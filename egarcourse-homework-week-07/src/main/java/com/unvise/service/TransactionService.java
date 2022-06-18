package com.unvise.service;

import com.unvise.domain.entity.Transaction;
import com.unvise.exception.TransactionFailedException;
import com.unvise.repository.TransactionRepository;
import com.unvise.service.manager.TransactionManagerImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService implements BaseService<Transaction, Long> {

    private final ApplicationContext applicationContext;

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = Collections.emptyList();
        try {
            log.debug("Getting all rows...");
            transactions = transactionRepository.findAll();
            log.debug("All rows are found");
        } catch (Exception e) {
            log.error("Exception occurred while getting rows", new RuntimeException(e));
        }
        return transactions;
    }

    @Override
    public Optional<Transaction> getById(Long aLong) {
        Optional<Transaction> transaction = Optional.empty();
        try {
            log.debug("Getting row by id: {}", aLong);
            transaction = transactionRepository.findById(aLong);
            log.debug("Row is found");
        } catch (Exception e) {
            log.error("Exception occurred while getting row with id: {}", aLong, new RuntimeException(e));

        }
        return transaction;
    }

    @Override
    public Transaction save(Transaction instance) throws TransactionFailedException {
        Transaction transactionToSave = null;
        try {
            TransactionManagerImpl transactionManager = applicationContext.getBean(TransactionManagerImpl.class);

            log.debug("Setting new transaction in transaction manager...");
            transactionManager.setTransaction(instance);

            log.debug("Performing transaction...");
            transactionManager.perform();

            transactionToSave = transactionRepository.save(instance);

            if (transactionManager.wasSuccessful()) {
                log.info("Transaction with id {}: was successful", transactionToSave.getId());
            } else {
                log.warn("Transaction with id {}: was failed", transactionToSave.getId());
            }
        } catch (Exception e) {
            log.error("Can't save a new instance {}", instance, new TransactionFailedException(e));
        }
        return transactionToSave;
    }

    @Override
    public Transaction deleteById(Long aLong) {
        Optional<Transaction> transaction = getById(aLong);
        try {
            log.info("Trying to deleting row with id: {}", aLong);
            transaction.ifPresent(transactionRepository::delete);
            log.info("Row with id: {} is deleted", aLong);
        } catch (Exception e) {
            log.error("Can't delete a with id: {}", aLong, new RuntimeException(e));
        }
        return transaction.orElse(null);
    }

}
