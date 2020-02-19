package com.revolut.mt.dao;

import java.util.Optional;
import com.revolut.mt.domain.Customer;

public interface CustomerDAO {

    public long addCustomer(Customer cust);

    public Optional<Customer> getCustomer(Long id);
}
