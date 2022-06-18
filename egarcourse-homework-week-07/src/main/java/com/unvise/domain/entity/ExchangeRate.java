package com.unvise.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "exchange_rate")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exchange_rate_seq_gen")
    @SequenceGenerator(name = "exchange_rate_seq_gen", sequenceName = "exchange_rate_seq_id", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "from_currency")
    @Enumerated(EnumType.STRING)
    private CurrencyType fromCurrency;

    @Column(name = "to_currency")
    @Enumerated(EnumType.STRING)
    private CurrencyType toCurrency;

    @Column(name = "rate")
    private BigDecimal rate;

}
