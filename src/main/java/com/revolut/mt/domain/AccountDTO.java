package com.revolut.mt.domain;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Data transfer object used for transactions.
 */

@JsonInclude(Include.NON_NULL)
public class AccountDTO {
    
    @JsonProperty("accountNumber")
    private String accountNumber;
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public AccountDTO() {}

    /**
     * @param accountNumber
     * @param amount
     */
    public AccountDTO(String accountNumber, BigDecimal amount) {
        super();
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    /**
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
