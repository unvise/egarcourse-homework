package com.unvise.domain.entity;

import java.math.BigDecimal;

public class Transaction {

    private Person fromPerson;

    private Person toPerson;

    private BigDecimal amount;

    private CurrencyType currency;

    public Transaction() {
    }

    public Transaction(Person fromPerson, Person toPerson, BigDecimal amount, CurrencyType currency) {
        this.fromPerson = fromPerson;
        this.toPerson = toPerson;
        this.amount = amount;
        this.currency = currency;
    }

    public Person getFromPerson() {
        return fromPerson;
    }

    public void setFromPerson(Person fromPerson) {
        this.fromPerson = fromPerson;
    }

    public Person getToPerson() {
        return toPerson;
    }

    public void setToPerson(Person toPerson) {
        this.toPerson = toPerson;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "fromPerson='" + fromPerson + '\'' +
                ", toPerson='" + toPerson + '\'' +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }

}
