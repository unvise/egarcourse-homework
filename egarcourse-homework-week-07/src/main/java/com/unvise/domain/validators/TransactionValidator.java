package com.unvise.domain.validators;

import com.unvise.domain.entity.Account;
import com.unvise.domain.entity.Transaction;

public class TransactionValidator {

    public static boolean isBalanceTransactionCorrect(Transaction transaction) {
        Account fromPersonAccount = transaction.getFromAccount();

        return fromPersonAccount.getBalance().compareTo(transaction.getAmount()) > -1;
    }

}
