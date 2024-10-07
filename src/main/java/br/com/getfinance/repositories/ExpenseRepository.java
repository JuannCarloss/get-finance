package br.com.getfinance.repositories;

import br.com.getfinance.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

    @Query("""
            SELECT combined.category, SUM(combined.amount) AS total_gasto
            FROM (
            SELECT e.amount AS amount, e.expenseCategory AS category
            FROM tb_expenses e
            UNION ALL
            SELECT i.amount AS amount, i.category AS category
            FROM tb_installments i
            ) AS combined
            GROUP BY combined.category
            ORDER BY total_gasto DESC
            """)
    List<Object[]> getAllMonthlyExpenses(Long userID);
}