package com.revolut.mt.domain;


public interface CustomerObjectMapper {
    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO custDTO);
}