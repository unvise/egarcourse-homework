package com.unvise.domain.converter;

import com.unvise.domain.entity.CurrencyType;
import com.unvise.domain.validators.CurrencyConverterValidator;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class CurrencyConverter {

    private final Map<Map<CurrencyType, CurrencyType>, BigDecimal> converterCurrencyMap = Map.of(
            Map.of(CurrencyType.RUB, CurrencyType.KZT), BigDecimal.valueOf(7.04),
            Map.of(CurrencyType.KZT, CurrencyType.RUB), BigDecimal.valueOf(0.14)
    );

    public BigDecimal convert(CurrencyType fromCurrency,
                              CurrencyType toCurrency,
                              BigDecimal value) {
        if (CurrencyConverterValidator.isCurrencyConverterOK(converterCurrencyMap, fromCurrency, toCurrency, value)) {
            return value.multiply(findQuotationValue(fromCurrency, toCurrency));
        }

        return value.multiply(BigDecimal.ONE);
    }

    private BigDecimal findQuotationValue(CurrencyType fromCurrency,
                                          CurrencyType toCurrency) throws RuntimeException {
        Set<Map<CurrencyType, CurrencyType>> currency = converterCurrencyMap.keySet();

        if (fromCurrency.equals(toCurrency)) {
            return BigDecimal.ONE;
        }

        for (Map<CurrencyType, CurrencyType> currencyTypeMap : currency) {
            if (currencyTypeMap.containsKey(fromCurrency) && currencyTypeMap.containsValue(toCurrency)) {
                return converterCurrencyMap.get(currencyTypeMap);
            }
        }
        throw new RuntimeException("Can't find a quotation for given currency types");
    }

}
