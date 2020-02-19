package com.revolut.mt.domain;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TransactionDTO {
    @JsonProperty("senderAccNum")
    private String senderAccNum;
    @JsonProperty("beneAccNum")
    private String beneAccNum;
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public TransactionDTO() {}


    /**
     * @param senderAccNum
     * @param beneAccNum
     * @param amount
     */
    public TransactionDTO(String senderAccNum, String beneAccNum, BigDecimal amount) {
        super();
        this.senderAccNum = senderAccNum;
        this.beneAccNum = beneAccNum;
        this.amount = amount;
    }

    /**
     * @return the senderAccNum
     */
    public String getSenderAccNum() {
        return senderAccNum;
    }

    /**
     * @param senderAccNum the senderAccNum to set
     */
    public void setSenderAccNum(String senderAccNum) {
        this.senderAccNum = senderAccNum;
    }

    /**
     * @return the beneAccNum
     */
    public String getBeneAccNum() {
        return beneAccNum;
    }

    /**
     * @param beneAccNum the beneAccNum to set
     */
    public void setBeneAccNum(String beneAccNum) {
        this.beneAccNum = beneAccNum;
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