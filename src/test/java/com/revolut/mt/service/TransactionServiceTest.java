package com.revolut.mt.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revolut.mt.dao.AccountDAO;
import com.revolut.mt.domain.Account;
import com.revolut.mt.domain.AccountDTO;
import com.revolut.mt.domain.TransactionDTO;
import com.revolut.mt.exception.InvalidAccountException;
import com.revolut.mt.exception.InvalidAmountException;

@ExtendWith(MockitoExtension.class)
@DisplayName("TransactionService Unit Test")
public class TransactionServiceTest {

    @Mock
    private AccountDAO acctDAO;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    @DisplayName("Should successfully withdraw a given amount")
    void testSuccessfullyWithdrawAmount() throws InvalidAccountException, InvalidAmountException {
        String accNum = "ACCTNUM";
        Account account = new Account(accNum, new BigDecimal("123.67"));
        when(acctDAO.getAccount(accNum)).thenReturn(Optional.of(account));
        AccountDTO amountDto = new AccountDTO(accNum, BigDecimal.TEN);
        Account account1 = transactionService.withdraw(amountDto);
        assertEquals(account1.getAmount(), new BigDecimal("113.67"));
        verify(acctDAO, times(1)).getAccount(accNum);
    }

    @Test
    @DisplayName("Should throw an exception in case of insufficient balance for withdrawal")
    void testThrowExceptionWhenNotEnoughAmount() {
        String accNum = "ACCTNUM";
        Account account = new Account(accNum, new BigDecimal("2.00"));
        when(acctDAO.getAccount(accNum)).thenReturn(Optional.of(account));
        AccountDTO amountDto = new AccountDTO(accNum, BigDecimal.TEN);
        assertThrows(InvalidAmountException.class, () -> transactionService.withdraw(amountDto));
        verify(acctDAO, times(1)).getAccount(accNum);
    }

    @Test
    @DisplayName("Should throw an exception if account not found while withdrawal")
    void testThrowExceptionWhenAccountDoesNotExistWithdrawal() {
        String accNum = "ACCTNUM";
        when(acctDAO.getAccount(accNum)).thenReturn(Optional.empty());
        AccountDTO amountDto = new AccountDTO(accNum, BigDecimal.TEN);
        assertThrows(InvalidAccountException.class, () -> transactionService.withdraw(amountDto));
        verify(acctDAO, times(1)).getAccount(accNum);
    }

    @Test
    @DisplayName("Should successfully deposit a given amount")
    void successfullyDepositAmount() throws InvalidAccountException, InvalidAmountException {
        String accNum = "ACCTNUM";
        Account account = new Account(accNum, new BigDecimal("200.00"));
        when(acctDAO.getAccount(accNum)).thenReturn(Optional.of(account));
        AccountDTO amountDto = new AccountDTO(accNum, new BigDecimal("500.00"));
        transactionService.deposit(amountDto);
        assertEquals(account.getAmount(), new BigDecimal("700.00"));
        verify(acctDAO, times(1)).getAccount(accNum);
        verifyNoMoreInteractions(acctDAO);
    }

    @Test
    @DisplayName("Should throw an exception in case of nonexistent account for deposit")
    void testThrowExceptionWhenAccountDoesNotExistDeposit() {
        String accNum = "ACCTNUM";
        when(acctDAO.getAccount(accNum)).thenReturn(Optional.empty());
        AccountDTO amountDto = new AccountDTO(accNum, BigDecimal.TEN);
        assertThrows(InvalidAccountException.class, () -> transactionService.deposit(amountDto));
        verify(acctDAO, times(1)).getAccount(accNum);
    }

    @Test
    @DisplayName("Should successfully transfer funds between accounts")
    void testSuccessfullyTransferAmount() throws InvalidAccountException, InvalidAmountException {
        String senderAcct = "NUM1";
        Account sender = new Account(senderAcct, new BigDecimal("50.00"));
        String receiverAcct = "NUM2";
        Account receiver = new Account(receiverAcct, new BigDecimal("20.00"));
        when(acctDAO.getAccount(senderAcct)).thenReturn(Optional.of(sender));
        when(acctDAO.getAccount(receiverAcct)).thenReturn(Optional.of(receiver));
        TransactionDTO transferDto = new TransactionDTO(senderAcct, receiverAcct, BigDecimal.TEN);
        transactionService.transfer(transferDto);
        assertEquals(transferDto.getAmount(), new BigDecimal("10"));
        assertEquals(receiver.getAmount(), new BigDecimal("30.00"));
        verify(acctDAO, times(1)).getAccount(senderAcct);
        verify(acctDAO, times(1)).getAccount(receiverAcct);
        verifyNoMoreInteractions(acctDAO);
    }

    @Test
    @DisplayName("Should throw an exception in case of insufficient funds for transfer")
    void testThrowExceptionWhenNotEnoughAmountForTransfer() {
        String senderAcct = "NUM1";
        Account sender = new Account(senderAcct, new BigDecimal("05.00"));
        String receiverAcct = "NUM2";
        when(acctDAO.getAccount(senderAcct)).thenReturn(Optional.of(sender));
        TransactionDTO transferDto = new TransactionDTO(senderAcct, receiverAcct, BigDecimal.TEN);
        assertThrows(InvalidAmountException.class, () -> transactionService.transfer(transferDto));
        verify(acctDAO, times(1)).getAccount(senderAcct);
        verifyNoMoreInteractions(acctDAO);
    }

}