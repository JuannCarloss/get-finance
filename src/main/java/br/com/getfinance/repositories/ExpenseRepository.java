package br.com.getfinance.repositories;

import br.com.getfinance.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

    @Query("""
            SELECT e.expenseCategory, SUM(amount) AS total_expenses
            FROM tb_expenses e
            WHERE e.date >= DATE_TRUNC('month', CURRENT_DATE)
            GROUP BY e.expenseCategory
            ORDER BY total_expenses DESC
            """)
    List<Object[]> getAllMonthlyExpenses(Long userID);
}