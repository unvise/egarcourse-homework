package com.unvise.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transaction")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq_gen")
    @SequenceGenerator(name = "transaction_seq_gen", sequenceName = "transaction_seq_id", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @OneToOne(orphanRemoval = true)
    @Lazy
    @JoinColumn(name = "from_account_id", foreignKey = @ForeignKey(name = "transaction_from_account_fk"))
    private Account fromAccount;

    @OneToOne(orphanRemoval = true)
    @Lazy
    @JoinColumn(name = "to_account_id", foreignKey = @ForeignKey(name = "transaction_to_account_fk"))
    private Account toAccount;

    @Column(name = "amount")
    private BigDecimal amount;

}
