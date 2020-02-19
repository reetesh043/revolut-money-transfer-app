package com.revolut.mt;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.revolut.mt.dao.AccountDAO;
import com.revolut.mt.dao.CustomerDAO;
import com.revolut.mt.domain.Account;
import com.revolut.mt.domain.TransactionDTO;
import com.revolut.mt.service.TransactionService;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class AppLoadTest {
    
    @Mock
    private AccountDAO acctDAO;

    @Mock
    private CustomerDAO custDAO;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void transfer() {
        assertEquals(1, 1);
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
    }
}
