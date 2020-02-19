package com.revolut.mt.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import java.util.HashSet;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revolut.mt.dao.AccountDAO;
import com.revolut.mt.dao.CustomerDAO;
import com.revolut.mt.domain.Account;
import com.revolut.mt.domain.Customer;
import com.revolut.mt.exception.CustomerNotFoundException;

@ExtendWith(MockitoExtension.class)
@DisplayName("AccountService Unit Test")
public class AccountServiceTest {

    @Mock
    private AccountDAO acctDAO;

    @Mock
    private CustomerDAO custDAO;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("Should successfully create account")
    void testSuccessfullyAccountCreation() throws CustomerNotFoundException {
        long id = 1L;
        Customer cust = new Customer(id, "reetesh", "kumar", "UK", new HashSet<>());
        when(custDAO.getCustomer(id)).thenReturn(Optional.of(cust));
        String accNum = accountService.createAccount(id);
        assertNotNull(accNum);
        verify(acctDAO, times(1)).addAccount(any(Account.class));
    }

    @Test
    @DisplayName("Should throw exception if not able to create account")
    void testThrowIfAccountCreationFails() throws CustomerNotFoundException {
        long id = 1L;
        assertThrows(CustomerNotFoundException.class, () -> accountService.createAccount(id));
    }

}