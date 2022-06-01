package com.unvise.domain.validators;

import com.unvise.domain.entity.CurrencyType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class CurrencyConverterValidator {

    public static boolean isCurrencyConverterOK(Map<Map<CurrencyType, CurrencyType>, BigDecimal> converterCurrencyMap,
                                                CurrencyType fromCurrency,
                                                CurrencyType toCurrency,
                                                BigDecimal value) {
        Set<Map<CurrencyType, CurrencyType>> currencyTypeMap = converterCurrencyMap.keySet();

        return currencyTypeMap.contains(Map.of(fromCurrency, toCurrency))
                && value.compareTo(BigDecimal.ZERO) >= 0;
    }

}
