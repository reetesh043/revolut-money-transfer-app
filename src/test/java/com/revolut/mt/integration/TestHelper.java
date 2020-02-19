package com.revolut.mt.integration;

import java.io.IOException;
import java.util.Objects;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestHelper {
    static final String CUSTOMER_URL = "http://localhost:9191/api/customer";
    static final String ACCOUNT_URL = "http://localhost:9191/api/account";

    static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static <T> T getResponseObject(Response response, ObjectMapper objectMapper, Class<T> clazz) throws IOException {
        String responseJson = Objects.requireNonNull(response.body()).string();
        return objectMapper.readValue(responseJson, clazz);
    }

    static String[] getCustomer(long custId, OkHttpClient client, ObjectMapper objectMapper) throws IOException {
        Request getRequest = new Request.Builder().url(CUSTOMER_URL + "/" + custId).get().build();
        Response response = client.newCall(getRequest).execute();
        return TestHelper.getResponseObject(response, objectMapper, String[].class);
    }
}