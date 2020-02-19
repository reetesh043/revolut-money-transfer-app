package com.revolut.mt.domain;

import javax.inject.Singleton;

@Singleton
public class CustomerObjectMapperImpl implements CustomerObjectMapper {

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setFirstName( customer.getFirstName() );
        customerDTO.setLastName( customer.getLastName() );
        customerDTO.setAddress( customer.getAddress() );

        return customerDTO;
    }

    @Override
    public Customer customerDTOToCustomer(CustomerDTO custDTO) {
        if ( custDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setFirstName( custDTO.getFirstName() );
        customer.setLastName( custDTO.getLastName() );
        customer.setAddress( custDTO.getAddress() );

        return customer;
    }
}