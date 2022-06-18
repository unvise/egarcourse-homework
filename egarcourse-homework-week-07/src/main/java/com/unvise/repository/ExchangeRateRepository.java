package com.unvise.repository;

import com.unvise.domain.entity.CurrencyType;
import com.unvise.domain.entity.ExchangeRate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends BaseRepository<ExchangeRate> {

    Optional<ExchangeRate> findByToCurrency(CurrencyType toCurrencyType);

}
