package com.unvise.service.manager;

import com.unvise.domain.entity.Account;
import com.unvise.domain.entity.ExchangeRate;
import com.unvise.domain.entity.Transaction;
import com.unvise.domain.validators.TransactionValidator;
import com.unvise.service.AccountService;
import com.unvise.service.ExchangeRateService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransactionManagerImpl implements TransactionManager {

    @Getter
    @Setter
    private Transaction transaction;

    private boolean isTransactionSuccessful = false;

    private final ExchangeRateService exchangeRateService;

    private final AccountService accountService;

    @Override
    public void perform() {
        Account fromAccount = transaction.getFromAccount();
        Account toAccount = transaction.getToAccount();

        isTransactionSuccessful = false;

        if (!TransactionValidator.isBalanceTransactionCorrect(transaction)) {
            return;
        }

        if (fromAccount.getCurrency().equals(toAccount.getCurrency())) {
            toAccount.addToBalance(transaction.getAmount());
            fromAccount.subtractFromBalance(transaction.getAmount());
        } else {
            Optional<ExchangeRate> exchangeRateFromToAccount = exchangeRateService.getByToCurrency(toAccount.getCurrency());
            System.out.println(exchangeRateFromToAccount);
            BigDecimal amountToAccount = transaction.getAmount().multiply(exchangeRateFromToAccount.get().getRate());
            toAccount.addToBalance(amountToAccount);
            fromAccount.subtractFromBalance(transaction.getAmount());
        }

        transaction = null;
        isTransactionSuccessful = true;

        accountService.save(fromAccount);
        accountService.save(toAccount);
    }

    @Override
    public boolean wasSuccessful() {
        return isTransactionSuccessful;
    }


}
