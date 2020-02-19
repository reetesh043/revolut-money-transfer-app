package com.revolut.mt.dao;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.inject.Singleton;
import com.revolut.mt.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Singleton
public class CustomerDAOImpl implements CustomerDAO{

    private static final Logger LOG = LoggerFactory.getLogger(CustomerDAOImpl.class);

    private final AtomicLong idGenerator = new AtomicLong(0);
    private final Map<Long, Customer> map = new ConcurrentHashMap<>();

    @Override
    public long addCustomer(Customer cust) {
        long id = idGenerator.incrementAndGet();
        map.put(id, cust);
        return id;
    }

    @Override
    public Optional<Customer> getCustomer(Long id) {
        LOG.info("Customer" + ":", map.get(id));
        return Optional.ofNullable(map.get(id));
    }
}