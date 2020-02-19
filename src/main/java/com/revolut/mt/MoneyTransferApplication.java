package com.revolut.mt;

import static com.revolut.mt.domain.ResultStatus.FAILURE;
import static io.jooby.StatusCode.BAD_REQUEST;
import static io.jooby.StatusCode.NOT_FOUND;

import com.revolut.mt.config.TransferInterfaceBindingModule;
import com.revolut.mt.controller.AccountController;
import com.revolut.mt.controller.CustomerController;
import com.revolut.mt.controller.TransactionController;
import com.revolut.mt.domain.Response;
import com.revolut.mt.exception.CustomerNotFoundException;
import com.revolut.mt.exception.InvalidAccountException;
import com.revolut.mt.exception.InvalidAmountException;
import com.revolut.mt.exception.InvalidRequestInputException;
import io.jooby.Jooby;
import io.jooby.di.GuiceModule;
import io.jooby.json.JacksonModule;

public class MoneyTransferApplication extends Jooby {
    {
        install(new JacksonModule());

        install(new GuiceModule(new TransferInterfaceBindingModule()));

        mvc(CustomerController.class);
        mvc(AccountController.class);
        mvc(TransactionController.class);


        error(CustomerNotFoundException.class, ((context, cause, statusCode) -> {
            context.setResponseCode(NOT_FOUND);
            context.render(new Response(FAILURE, cause.getMessage()));
        }));

        error(InvalidAccountException.class, ((context, cause, statusCode) -> {
            context.setResponseCode(BAD_REQUEST);
            context.render(new Response(FAILURE, cause.getMessage()));
        }));

        error(InvalidAmountException.class, ((context, cause, statusCode) -> {
            context.setResponseCode(BAD_REQUEST);
            context.render(new Response(FAILURE, cause.getMessage()));
        }));

        error(InvalidRequestInputException.class, ((context, cause, statusCode) -> {
            context.setResponseCode(BAD_REQUEST);
            context.render(new Response(FAILURE, cause.getMessage()));
        }));
    }

    public static void main(String[] args) {
        runApp(args, MoneyTransferApplication::new);
    }
}
