package com.unvise.domain.entity;

public enum CurrencyType {

    RUB("RUB"), KZT("KZT");

    private final String currencyAsString;

    CurrencyType(String currencyAsString) {
        this.currencyAsString = currencyAsString;
    }

    public String getCurrencyAsString() {
        return currencyAsString;
    }

}
