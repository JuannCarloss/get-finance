package br.com.getfinance.models;

import br.com.getfinance.enums.ExpenseCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_budget")
public class Budget extends EntityID {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "installment")
    private int installmentQuantity;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    @OneToMany(mappedBy = "budget", fetch = FetchType.EAGER)
    private List<Installment> installments;
}
