package com.revolut.mt.service;

import java.math.BigDecimal;
import javax.inject.Singleton;
import com.revolut.mt.domain.AccountDTO;
import com.revolut.mt.domain.TransactionDTO;
import com.revolut.mt.exception.InvalidRequestInputException;

@Singleton
public class ValidationService {

    public void validate(AccountDTO acct) throws InvalidRequestInputException {
        validateAmount(acct.getAmount());
        validateAccountNumber(acct.getAccountNumber());
    }

    public void validate(TransactionDTO amountDTO) throws InvalidRequestInputException {
        validateAmount(amountDTO.getAmount());
        validateAccountNumber(amountDTO.getSenderAccNum());
        validateAccountNumber(amountDTO.getBeneAccNum());
    }

    public void validateAccountNumber(String acctNum){
        if (acctNum == null || acctNum.isEmpty()) {
            throw new InvalidRequestInputException("Null or Empty can't be a valid Account number.");
        }

        if (acctNum.length() < 8) {
            throw new InvalidRequestInputException("Account number should be of length 8");
        }
    }

    private void validateAmount(BigDecimal amount) throws InvalidRequestInputException {
        if (amount == null) {
            throw new InvalidRequestInputException("Amount can not be empty");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidRequestInputException("Amount can not be negative value");
        }
    }

}