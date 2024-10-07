package br.com.getfinance.dtos;

import br.com.getfinance.models.Budget;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDTO {

    private Long id;
    private String name;
    private Double totalAmount;
    private int installmentQuantity;
    @Enumerated(EnumType.STRING)
    private String expenseCategory;

    public static BudgetDTO fromEntity(Budget budget){
        return new BudgetDTO(
                budget.getId(),
                budget.getName(),
                budget.getTotalAmount(),
                budget.getInstallmentQuantity(),
                budget.getCategory().toString());
    }
}
