package com.revolut.mt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class Response {

    @JsonProperty("Status")
    private ResultStatus status;

    @JsonProperty("Message")
    private String message;

    /**
     * @param status
     * @param message
     */
    public Response(ResultStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the status
     */
    public ResultStatus getStatus() {
        return status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Response [status=" + status + ", message=" + message + "]";
    }
}
