package br.com.getfinance.models;

import br.com.getfinance.enums.ExpenseCategory;
import br.com.getfinance.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "tb_expenses")
public class Expense extends EntityID{

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userID;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "description")
    private String description;

    @Column(name = "expense_date")
    private LocalDate date;

    public Expense(){
        this.date = LocalDate.now();
    }

}
