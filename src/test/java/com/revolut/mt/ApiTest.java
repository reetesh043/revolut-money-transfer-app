package com.revolut.mt;

import static org.hamcrest.CoreMatchers.containsString;
import com.revolut.mt.domain.Account;
import com.revolut.mt.domain.AccountDTO;
import com.revolut.mt.domain.CustomerDTO;
import com.revolut.mt.domain.Response;
import com.revolut.mt.domain.TransactionDTO;
import com.revolut.mt.service.AccountService;
import com.revolut.mt.service.CustomerService;
import com.revolut.mt.service.TransactionService;
import io.jooby.StatusCode;
import io.restassured.mapper.ObjectMapperType;

import static io.restassured.RestAssured.given;

public abstract class ApiTest {
    public static String TRANSACTION_API_PATH = "/api";
    static String CONTENT_TYPE_JSON = "application/json";

    public abstract MoneyTransferApplication getApplication();

    public long addCustomer(CustomerDTO custDTO) {
        return getApplication().require(CustomerService.class).addCustomer(custDTO);
    }

    public String createAccount(long custId) {
        return getApplication().require(AccountService.class).createAccount(custId);
    }

    public Account deposit(AccountDTO request) {
        return getApplication().require(TransactionService.class).deposit(request);
    }

    public Account withdraw(AccountDTO request) {
        return getApplication().require(TransactionService.class).withdraw(request);
    }

    public void transfer(TransactionDTO request) {
        getApplication().require(TransactionService.class).transfer(request);
    }

    public AccountDTO getBalance(String accNum) {
        return getApplication().require(AccountService.class).checkAvailableBalance(accNum);
    }

    public void executeTransferWithBadRequest(TransactionDTO request, String errorMessage) {
        given().when().body(request).contentType(CONTENT_TYPE_JSON).post(TRANSACTION_API_PATH + "/transfer").then().statusCode(StatusCode.BAD_REQUEST.value()).body(containsString(errorMessage));
    }

    public Response executeTransferWithSuccess(TransactionDTO request) {
        return given().when().body(request).contentType(CONTENT_TYPE_JSON).post(TRANSACTION_API_PATH + "/transfer").then().statusCode(StatusCode.OK.value()).extract().response().as(Response.class, ObjectMapperType.JACKSON_2);
    }
}