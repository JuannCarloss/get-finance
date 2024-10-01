package br.com.getfinance.services;

import br.com.getfinance.dtos.MonthlyExpenseDTO;
import br.com.getfinance.models.Expense;

import java.time.LocalDate;
import java.util.List;

public interface IExpenseService {

    Expense save(Expense expense);
    List<Expense> filterAndListAllExpenses(Long userID, String category, String type, LocalDate start, LocalDate end);
    List<MonthlyExpenseDTO> findAllMonthlyExpenses(Long id);
}
