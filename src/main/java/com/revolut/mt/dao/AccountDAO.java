package com.revolut.mt.dao;

import java.util.Optional;
import com.revolut.mt.domain.Account;

public interface AccountDAO {
    
    public void addAccount(Account account);
    public Optional<Account> getAccount(String accNum);
    public int noOfAccounts();

}
