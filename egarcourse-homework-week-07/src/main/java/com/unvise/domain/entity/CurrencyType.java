package com.unvise.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CurrencyType {

    RUB("RUB"), KZT("KZT");

    private final String currencyAsString;

}
