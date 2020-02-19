/*package com.revolut.mt;


import com.revolut.mt.domain.AccountDTO;
import com.revolut.mt.domain.CustomerDTO;
import com.revolut.mt.domain.TransactionDTO;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;


public class TransactionConcurrencyTest extends ApiTest {

    private int dataCount = 1;
    private int treadCount = 1;

    MoneyTransferApplication application = new MoneyTransferApplication();

    //MockRouter router = new MockRouter(application);

    @Override
    public MoneyTransferApplication getApplication() {
        return application;
    }

    @Test
    public void transfer_givenMultipleTransferBetweenTwoAccounts_thenSuccess() throws InterruptedException {
        long id = addCustomer(new CustomerDTO("reetesh", "kumar", "UK"));
        String firstAccount = createAccount(id);
        String secondAccount = createAccount(id);
        deposit(new AccountDTO(firstAccount, BigDecimal.valueOf(1000)));
        deposit(new AccountDTO(secondAccount, BigDecimal.valueOf(1000)));

        ExecutorService executor = Executors.newFixedThreadPool(treadCount);
        CountDownLatch latch = new CountDownLatch(dataCount * 2);
        for (int i = 0; i < dataCount; i++) {
            executeTransferThreadWithSuccess(executor, latch, new TransactionDTO(firstAccount, secondAccount, BigDecimal.valueOf(1.11)));
            executeTransferThreadWithSuccess(executor, latch, new TransactionDTO(secondAccount, firstAccount, BigDecimal.valueOf(2.22)));
        }
        latch.await();

        AccountDTO firstBalance = getBalance(firstAccount);
        AccountDTO secondBalance = getBalance(secondAccount);
        assertTrue(BigDecimal.valueOf(1111).compareTo(firstBalance.getAmount()) == 0);
        assertTrue(BigDecimal.valueOf(889).compareTo(secondBalance.getAmount()) == 0);
    }

    public void executeTransferThreadWithSuccess(ExecutorService executor, CountDownLatch latch, TransactionDTO request) {
        executor.execute(new Thread(() -> {
            try {
                executeTransferWithSuccess(request);
                latch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

}*/