package br.com.getfinance.models;

import br.com.getfinance.enums.ExpenseCategory;
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

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private InstallmentStatus installmentStatus;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    public Installment (Budget budget, Long userID, Double amount, LocalDate date, ExpenseCategory expenseCategory) {
        this.budget = budget;
        this.userID = userID;
        this.amount = amount;
        this.date = date;
        this.installmentStatus = InstallmentStatus.PAGAR;
        this.category = expenseCategory;
    }
}
