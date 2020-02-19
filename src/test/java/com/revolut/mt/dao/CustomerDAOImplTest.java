package com.revolut.mt.dao;

import com.revolut.mt.domain.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import java.util.HashSet;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("CustomerDAOImpl Unit Test")
class CustomerDAOImplTest {

    @InjectMocks
    private CustomerDAOImpl customerDAO;

    @Mock
    private Logger logger;

    @Test
    @DisplayName("Add new customer")
    void addCustomer() {
        long id = 1L;
        Customer cust = new Customer(id, "reetesh", "kumar", "UK", new HashSet<>());
        long value = customerDAO.addCustomer(cust);
        assertEquals(id, value);
    }

    @Test
    @DisplayName("Get existing customer")
    void getCustomer() {
        long id = 1L;
        Optional<Customer> customer = customerDAO.getCustomer(id);
        assertNotNull(customer);
    }
}