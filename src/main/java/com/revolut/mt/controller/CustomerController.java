package com.revolut.mt.controller;

import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revolut.mt.domain.CustomerDTO;
import com.revolut.mt.domain.Response;
import com.revolut.mt.domain.ResultStatus;
import com.revolut.mt.exception.CustomerNotFoundException;
import com.revolut.mt.service.CustomerService;
import io.jooby.Context;
import io.jooby.MediaType;
import io.jooby.StatusCode;
import io.jooby.annotations.GET;
import io.jooby.annotations.PUT;
import io.jooby.annotations.Path;
import io.jooby.annotations.PathParam;

/**
 * Rest controller for customer details.
 */

@Singleton
@Path("/api/customer")
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    private  CustomerService custService;

    @Inject
    public CustomerController(CustomerService custService) {
        this.custService = custService;
    }

    @GET("/{id}")
    public CustomerDTO getCustomer(@PathParam Long id) throws CustomerNotFoundException {
        return custService.getCustomer(id);
    }

    @GET("/{id}/accounts")
    public Set<String> getAccounts(@PathParam Long id) throws CustomerNotFoundException {
        return custService.getAccounts(id);
    }

    @PUT
    public Response addCustomer(CustomerDTO custDTO, Context context) {
        long id = custService.addCustomer(custDTO);
        context.setResponseType(MediaType.JSON);
        context.setResponseCode(StatusCode.CREATED);
        LOG.info("Customer is created successfully  with customerID:" + id);
        return new Response(ResultStatus.SUCCESS, "Customer is created successfully  with customerID:" + id);
    }

}
