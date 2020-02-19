package com.revolut.mt.dao;

import com.revolut.mt.domain.Account;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Singleton;

@Singleton
public class AccountDaoImpl implements AccountDAO {

    private final Map<String, Account> map = new ConcurrentHashMap<>();

    @Override
    public int noOfAccounts() {
        return map.size();
    }

    @Override
    public void addAccount(Account account) {
        map.put(account.getAccountNumber(), account);
    }

    @Override
    public Optional<Account> getAccount(String accNum) {
        return Optional.ofNullable(map.get(accNum));
    }

}
