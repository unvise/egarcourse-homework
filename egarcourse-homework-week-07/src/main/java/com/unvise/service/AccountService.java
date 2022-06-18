package com.unvise.service;

import com.unvise.domain.entity.Account;
import com.unvise.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService implements BaseService<Account, Long> {

    private final AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        List<Account> accounts = Collections.emptyList();
        try {
            log.debug("Getting all rows...");
            accounts = accountRepository.findAll();
            log.debug("All rows are found");
        } catch (Exception e) {
            log.error("Exception occurred while getting rows", new RuntimeException(e));
        }

        return accounts;
    }

    @Override
    public Optional<Account> getById(Long aLong) {
        Optional<Account> account = Optional.empty();
        try {
            log.debug("Getting row by id: {}", aLong);
            account = accountRepository.findById(aLong);
            log.debug("Row is found");
        } catch (Exception e) {
            log.error("Exception occurred while getting row with id: {}", aLong, new RuntimeException(e));
        }

        return account;
    }

    @Override
    public Account save(Account instance) {
        Account accountToSave = null;
        try {
            log.info("Saving instance: {}", instance);
            accountToSave = accountRepository.save(instance);
            log.info("Successfully save new instance: {}", instance);
        } catch (Exception e) {
            log.error("Can't save a new instance {}", instance, new RuntimeException(e));
        }

        return accountToSave;
    }

    @Override
    public Account deleteById(Long aLong) {
        Optional<Account> account = getById(aLong);
        try {
            log.info("Trying to deleting row with id: {}", aLong);
            account.ifPresent(accountRepository::delete);
            log.info("Row with id: {} is deleted", aLong);
        } catch (Exception e) {
            log.error("Can't delete a with id: {}", aLong, new RuntimeException(e));
        }

        return account.orElse(null);
    }
}
