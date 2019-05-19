package com.donaldo.bookfinder.bookapi.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetailPrice {

    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}
