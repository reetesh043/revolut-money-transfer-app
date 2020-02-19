package com.revolut.mt.controller;

import io.jooby.Context;
import io.jooby.annotations.*;
import static io.jooby.StatusCode.CREATED;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.revolut.mt.domain.AccountDTO;
import com.revolut.mt.domain.Response;
import com.revolut.mt.domain.ResultStatus;
import com.revolut.mt.exception.CustomerNotFoundException;
import com.revolut.mt.exception.InvalidAccountException;
import com.revolut.mt.service.AccountService;
import com.revolut.mt.service.ValidationService;

@Singleton
@Path("/api/account")
public class AccountController {

    private final AccountService accountService;
    private final ValidationService validationService;

    @Inject
    public AccountController(AccountService accountService, ValidationService validationService) {
        this.accountService = accountService;
        this.validationService = validationService;
    }

    @PUT("/create/{id}")
    public Response createAccount(@PathParam Long id, Context context) throws CustomerNotFoundException {
        String accNum = accountService.createAccount(id);
        context.setResponseCode(CREATED);
        return new Response(ResultStatus.SUCCESS, "Account created successfully with accountNumber: " + accNum);
    }

    @GET("/balance/{accNum}")
    public AccountDTO checkFunds(@PathParam String accNum) throws InvalidAccountException {
        validationService.validateAccountNumber(accNum);
        return accountService.checkAvailableBalance(accNum);
    }

}