package com.unvise.service;

import com.unvise.domain.entity.CurrencyType;
import com.unvise.domain.entity.ExchangeRate;
import com.unvise.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateService implements BaseService<ExchangeRate, Long> {

    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<ExchangeRate> getAll() {
        List<ExchangeRate> rates = Collections.emptyList();
        try {
            log.debug("Getting all rows...");
            rates = exchangeRateRepository.findAll();
            log.debug("All rows are found");
        } catch (Exception e) {
            log.error("Exception occurred while getting rows", new RuntimeException(e));
        }
        return rates;
    }

    @Override
    public Optional<ExchangeRate> getById(Long aLong) {
        Optional<ExchangeRate> rate = Optional.empty();
        try {
            log.debug("Getting row by id: {}", aLong);
            rate = exchangeRateRepository.findById(aLong);
            log.debug("Row is found");
        } catch (Exception e) {
            log.error("Exception occurred while getting row with id: {}", aLong, new RuntimeException(e));

        }
        return rate;
    }

    @Override
    public ExchangeRate save(ExchangeRate instance) {
        ExchangeRate rateToSave = null;
        try {
            log.info("Saving instance: {}", instance);
            rateToSave = exchangeRateRepository.save(instance);
            log.info("Successfully save new instance: {}", instance);
        } catch (Exception e) {
            log.error("Can't save a new instance {}", instance, new RuntimeException(e));
        }
        return rateToSave;
    }

    @Override
    public ExchangeRate deleteById(Long aLong) {
        Optional<ExchangeRate> rate = getById(aLong);
        try {
            log.info("Trying to deleting row with id: {}", aLong);
            rate.ifPresent(exchangeRateRepository::delete);
            log.info("Row with id: {} is deleted", aLong);
        } catch (Exception e) {
            log.error("Can't delete a with id: {}", aLong, new RuntimeException(e));
        }
        return rate.orElse(null);
    }

    public Optional<ExchangeRate> getByToCurrency(CurrencyType toCurrencyType) {
        Optional<ExchangeRate> rate = Optional.empty();
        try {
            log.debug("Getting row by toCurrency: {}", toCurrencyType);
            rate = exchangeRateRepository.findByToCurrency(toCurrencyType);
            log.debug("Row is found");
        } catch (Exception e) {
            log.error("Can't find row with toCurrency: {}", toCurrencyType);
        }
        return rate;
    }

}
