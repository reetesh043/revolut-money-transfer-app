package com.revolut.mt.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revolut.mt.exception.InvalidRequestInputException;

@ExtendWith(MockitoExtension.class)
@DisplayName("ValidationService Unit Test")
public class ValidationServiceTest {

    @InjectMocks
    private ValidationService valid;

    @Test
    @DisplayName("Should throw exception if account number is null")
    public void testNullAccountNumber() {
        assertThrows(InvalidRequestInputException.class, () -> valid.validateAccountNumber(null));

    }

    @Test
    @DisplayName("Should throw exception if account number is empty")
    public void testEmptyAccountNumber() {
        assertThrows(InvalidRequestInputException.class, () -> valid.validateAccountNumber(""));

    }

}
