package com.unvise.repository.customRepository.exchange_rate;

import com.unvise.domain.entity.ExchangeRate;
import com.unvise.repository.customRepository.CustomRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CustomExchangeRateRepository extends CustomRepository<ExchangeRate, Long> {

    List<ExchangeRate> findExchangeRateByRateBetween(BigDecimal smallest, BigDecimal biggest);

}
