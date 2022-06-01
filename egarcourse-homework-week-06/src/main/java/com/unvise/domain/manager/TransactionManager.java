package com.unvise.domain.manager;

import com.unvise.domain.converter.CurrencyConverter;
import com.unvise.domain.entity.Account;
import com.unvise.domain.entity.Transaction;
import com.unvise.domain.validators.TransactionValidator;

import java.math.BigDecimal;

public class TransactionManager implements Manager {

    private Transaction transaction;
    private boolean isTransactionSuccessful = false;

    public TransactionManager() {
    }

    public TransactionManager(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public boolean isTransactionSuccessful() {
        return isTransactionSuccessful;
    }

    public void setTransactionSuccessful(boolean transactionSuccessful) {
        isTransactionSuccessful = transactionSuccessful;
    }

    @Override
    public void complete() {
        Account fromPersonAccount = transaction.getFromPerson().getAccount();
        Account toPerson = transaction.getToPerson().getAccount();

        isTransactionSuccessful = false;

        if (TransactionValidator.isBalanceTransactionCorrect(transaction)) {
            CurrencyConverter converter = new CurrencyConverter();

            BigDecimal transactionBalance = converter.convert(
                    transaction.getFromPerson().getAccount().getCurrency(),
                    transaction.getToPerson().getAccount().getCurrency(),
                    transaction.getAmount()
            );

            toPerson.addToBalance(transactionBalance);
            fromPersonAccount.subtractFromBalance(transaction.getAmount());

            transaction = null;
            isTransactionSuccessful = true;
        }
    }

    @Override
    public boolean wasSuccessful() {
        return isTransactionSuccessful;
    }

}
