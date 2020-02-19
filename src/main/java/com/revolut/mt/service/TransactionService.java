package com.revolut.mt.service;

import java.math.BigDecimal;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.revolut.mt.dao.AccountDAO;
import com.revolut.mt.domain.Account;
import com.revolut.mt.domain.AccountDTO;
import com.revolut.mt.domain.TransactionDTO;
import com.revolut.mt.exception.InvalidAccountException;
import com.revolut.mt.exception.InvalidAmountException;

@Singleton
public class TransactionService {

    private AccountDAO accDAO;

    @Inject
    public TransactionService(AccountDAO accDAO) {
        this.accDAO = accDAO;
    }

    public synchronized Account withdraw(AccountDTO account) throws InvalidAccountException, InvalidAmountException {
        Account acct = getAccountOrElseThrow(account.getAccountNumber());
        BigDecimal amount = acct.getAmount().subtract(account.getAmount());
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException("Available Balance is insufficient for  withdrawal with accountNumber: " + account.getAccountNumber());
        } else {
            acct.setAmount(amount);
        }

        return acct;
    }

    public synchronized Account deposit(AccountDTO accDTO) throws InvalidAccountException {
        Account account = getAccountOrElseThrow(accDTO.getAccountNumber());
        BigDecimal amount = account.getAmount().add(accDTO.getAmount());
        account.setAmount(amount);
        return account;
    }

    public synchronized void transfer(TransactionDTO transferDto) throws InvalidAccountException, InvalidAmountException {
        withdraw(new AccountDTO(transferDto.getSenderAccNum(), transferDto.getAmount()));
        deposit(new AccountDTO(transferDto.getBeneAccNum(), transferDto.getAmount()));
    }

    private Account getAccountOrElseThrow(String accountNumber) throws InvalidAccountException {
        return accDAO.getAccount(accountNumber).orElseThrow(() -> new InvalidAccountException("No account found with given account number: " + accountNumber));
    }

}
