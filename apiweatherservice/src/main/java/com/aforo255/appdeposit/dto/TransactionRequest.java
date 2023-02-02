package com.aforo255.appdeposit.dto;

import java.io.Serializable;

public class TransactionRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer accountId;
    private double amount;

    public TransactionRequest() {

    }

    public TransactionRequest(Integer accountId, double amount) {
        this.setAccountId(accountId);
        this.setAmount(amount);
    }

    public Integer getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
