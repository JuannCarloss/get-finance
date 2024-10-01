package br.com.getfinance.models;

import br.com.getfinance.enums.InstallmentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_installments")
@SQLRestriction("status = 'PAGAR'")
public class Installment extends EntityID{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "budget_id")
    @JsonIgnore
    private Budget budget;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private InstallmentStatus installmentStatus;

    public Installment (Budget budget, Double amount, LocalDate date) {
        this.budget = budget;
        this.amount = amount;
        this.date = date;
        this.installmentStatus = InstallmentStatus.PAGAR;
    }
}
