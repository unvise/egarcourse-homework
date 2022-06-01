package com.unvise.domain.entity;

import java.math.BigDecimal;

public class Account {

    private Integer id;

    private BigDecimal balance;

    private CurrencyType currency;

    public Account() {
    }

    public Account(Integer id, BigDecimal balance, CurrencyType currency) {
        this.id = id;
        this.balance = balance;
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public void addToBalance(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public void subtractFromBalance(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", currency=" + currency +
                '}';
    }

}
