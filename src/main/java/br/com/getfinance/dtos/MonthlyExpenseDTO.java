package br.com.getfinance.dtos;

import br.com.getfinance.enums.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyExpenseDTO {

    private ExpenseCategory category;
    private Double totalExpenses;

    public static MonthlyExpenseDTO fromEntity(Object[] expense){
        return new MonthlyExpenseDTO(
                (ExpenseCategory) expense[0],
                (Double) expense[1]);
    }
}
