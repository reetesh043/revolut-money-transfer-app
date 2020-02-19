package com.revolut.mt.service;

import org.apache.commons.lang.RandomStringUtils;
import com.revolut.mt.dao.AccountDAO;
import com.revolut.mt.dao.CustomerDAO;
import com.revolut.mt.domain.Account;
import com.revolut.mt.domain.AccountDTO;
import com.revolut.mt.exception.CustomerNotFoundException;
import com.revolut.mt.exception.InvalidAccountException;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;

@Singleton
public class AccountService {

    private AccountDAO accDAO;
    private CustomerDAO custDAO;

    @Inject
    public AccountService(AccountDAO accDAO, CustomerDAO custDAO) {
        this.accDAO = accDAO;
        this.custDAO = custDAO;
    }

    public synchronized String createAccount(long custId) throws CustomerNotFoundException {
        String generatedString = RandomStringUtils.randomNumeric(8);
        String accountNum = maskAccountNumber(generatedString);
        custDAO.getCustomer(custId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with customerID:" + custId));
        accDAO.addAccount(new Account(accountNum, BigDecimal.ZERO));
        return accountNum;
    }

    public AccountDTO checkAvailableBalance(String accNum) throws InvalidAccountException {
        return accDAO.getAccount(accNum).map(account -> new AccountDTO(accNum, account.getAmount())).orElseThrow(() -> new InvalidAccountException("No account found with given accountNumber: " + accNum));
    }


    private static String maskAccountNumber(String accNum) {
        int length = accNum.length();
        int lastSixIndex = length - 6;
        StringBuilder maskedStr = new StringBuilder(accNum.substring(0, lastSixIndex));
        for (int i = lastSixIndex; i < lastSixIndex + 4; i++) {
            maskedStr.append("X");
        }
        maskedStr.append(accNum.substring(length - 2));
        return maskedStr.toString();
    }

}