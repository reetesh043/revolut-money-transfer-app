package com.revolut.mt.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import com.revolut.mt.dao.CustomerDAO;
import com.revolut.mt.domain.Customer;
import com.revolut.mt.domain.CustomerDTO;
import com.revolut.mt.domain.CustomerObjectMapper;
import com.revolut.mt.exception.CustomerNotFoundException;
import java.util.Set;

@Singleton
public class CustomerService {
    private CustomerObjectMapper objMapper;
    private CustomerDAO custDAO;


    @Inject
    public CustomerService(CustomerObjectMapper objMapper, CustomerDAO custDAO) {
        this.objMapper = objMapper;
        this.custDAO = custDAO;
    }

    public long addCustomer(CustomerDTO custDTO) {
        Customer customer = objMapper.customerDTOToCustomer(custDTO);
        return custDAO.addCustomer(customer);
    }

    public CustomerDTO getCustomer(Long id) throws CustomerNotFoundException {
        return custDAO.getCustomer(id).map(objMapper::customerToCustomerDTO).orElseThrow(() -> new CustomerNotFoundException("Customer not found with customerid: " + id));
    }

    public Set<String> getAccounts(Long id) throws CustomerNotFoundException {
        return custDAO.getCustomer(id).map(Customer::getAccounts).orElseThrow(() -> new CustomerNotFoundException("Customer not found with customerid: " + id));
    }

}