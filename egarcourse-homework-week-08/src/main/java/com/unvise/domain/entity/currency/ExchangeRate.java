package com.unvise.domain.entity.currency;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exc_rate_seq_gen")
    @SequenceGenerator(name = "exc_rate_seq_gen", sequenceName = "exc_rate_seq_id", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "from_currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyType fromCurrency;

    @Column(name = "to_currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyType toCurrency;

    @Column(name = "ratio", nullable = false)
    private BigDecimal ratio;

}
