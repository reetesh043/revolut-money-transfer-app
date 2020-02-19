package com.revolut.mt.service;

import java.util.HashSet;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revolut.mt.dao.CustomerDAO;
import com.revolut.mt.domain.Customer;
import com.revolut.mt.domain.CustomerDTO;
import com.revolut.mt.domain.CustomerObjectMapper;
import com.revolut.mt.exception.CustomerNotFoundException;

@ExtendWith(MockitoExtension.class)
@DisplayName("CustomerServiceImpl Unit Test")
public class CustomerServiceTest {
    @Mock
    private CustomerDAO custDAO;

    @Spy
    private CustomerObjectMapper objMapper = Mappers.getMapper(CustomerObjectMapper.class);

    @InjectMocks
    private CustomerService custService;

    @Test
    @DisplayName("Should return an existing customer")
    void testCustomerExists() throws CustomerNotFoundException {
        long id = 1L;
        Customer cust = new Customer(id, "reetesh", "kumar", "UK", new HashSet<>());
        when(custDAO.getCustomer(id)).thenReturn(Optional.of(cust));
        CustomerDTO userDto = custService.getCustomer(id);
        assertEquals(userDto.getFirstName(), cust.getFirstName());
        assertEquals(userDto.getLastName(), cust.getLastName());
        assertEquals(userDto.getAddress(), cust.getAddress());
        verify(custDAO, Mockito.times(1)).getCustomer(anyLong());
        verifyNoMoreInteractions(custDAO);
    }

    @Test
    @DisplayName("Add customer")
    void testIfCustomerAdded() throws CustomerNotFoundException {
        long id = 1L;
        CustomerDTO custDTO = new CustomerDTO("reetesh", "kumar", "UK");
        when(custDAO.addCustomer(any())).thenReturn(id);
        long value = custService.addCustomer(custDTO);
        assertEquals(value, id);
        verify(custDAO, Mockito.times(1)).addCustomer(any());
    }

    @Test
    @DisplayName("Should throw an exception in case of customer does not exist")
    void testCustomerDoesNotExist() {
        long id = 1L;
        assertThrows(CustomerNotFoundException.class, () -> custService.getCustomer(id));
        verify(custDAO, Mockito.times(1)).getCustomer(anyLong());
    }
}