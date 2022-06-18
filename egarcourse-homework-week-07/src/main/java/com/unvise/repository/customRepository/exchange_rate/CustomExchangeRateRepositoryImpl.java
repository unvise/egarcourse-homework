package com.unvise.repository.customRepository.exchange_rate;

import com.unvise.domain.entity.ExchangeRate;
import com.unvise.repository.customRepository.AbstractCustomRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class CustomExchangeRateRepositoryImpl extends AbstractCustomRepository implements CustomExchangeRateRepository {


    @Override
    public List<ExchangeRate> findAll() {
        return getEntityManager()
                .createQuery("select e from ExchangeRate e", ExchangeRate.class)
                .getResultList();
    }

    @Override
    public List<ExchangeRate> findExchangeRateByRateBetween(BigDecimal smallest, BigDecimal biggest) {
        return getEntityManager()
                .createQuery("select e from ExchangeRate e " +
                        "where e.rate between :smallest and :biggest", ExchangeRate.class)
                .setParameter("smallest", smallest)
                .setParameter("biggest", biggest)
                .getResultList();
    }
}
