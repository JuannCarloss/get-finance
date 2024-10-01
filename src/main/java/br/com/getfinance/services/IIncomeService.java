package br.com.getfinance.services;

import br.com.getfinance.models.Income;

import java.time.LocalDate;
import java.util.List;

public interface IIncomeService {

    Income create(Income income);
    List<Income> filterAndGetAllIncomes(Long id, String category, LocalDate start, LocalDate end);
}
