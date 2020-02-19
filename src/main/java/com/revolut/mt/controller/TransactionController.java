package com.revolut.mt.controller;

import static com.revolut.mt.domain.ResultStatus.SUCCESS;
import static io.jooby.StatusCode.ACCEPTED;
import static io.jooby.StatusCode.OK;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.revolut.mt.domain.Account;
import com.revolut.mt.domain.AccountDTO;
import com.revolut.mt.domain.Response;
import com.revolut.mt.domain.TransactionDTO;
import com.revolut.mt.exception.InvalidAccountException;
import com.revolut.mt.exception.InvalidAmountException;
import com.revolut.mt.exception.InvalidRequestInputException;
import com.revolut.mt.service.TransactionService;
import com.revolut.mt.service.ValidationService;
import io.jooby.Context;
import io.jooby.annotations.POST;
import io.jooby.annotations.Path;

@Singleton
@Path("/api")
public class TransactionController {

    private final TransactionService transferService;
    private final ValidationService validationService;

    @Inject
    public TransactionController(TransactionService transferService, ValidationService validationService) {
        this.transferService = transferService;
        this.validationService = validationService;
    }

    @POST("/transfer")
    public Response transfer(TransactionDTO transferDto, Context context)
            throws InvalidAccountException, InvalidAmountException, InvalidRequestInputException {
        validationService.validate(transferDto);
        transferService.transfer(transferDto);
        context.setResponseCode(OK);
        return new Response(SUCCESS, "Amount transferred successfully");
    }

    @POST("/withdraw")
    public Account withdraw(AccountDTO accDTO, Context context) throws InvalidAccountException, InvalidAmountException, InvalidRequestInputException {
        validationService.validate(accDTO);
        Account account = transferService.withdraw(accDTO);
        context.setResponseCode(OK);
        return account;
    }

    @POST("/deposit")
    public Account deposit(AccountDTO accDTO, Context context) throws InvalidAccountException, InvalidAmountException, InvalidRequestInputException {
        validationService.validate(accDTO);
        Account account = transferService.deposit(accDTO);
        context.setResponseCode(ACCEPTED);
        return account;
    }

}
