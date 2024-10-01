package br.com.getfinance.services;

import br.com.getfinance.models.Budget;

import java.util.List;


public interface IBudgetService {

    Budget create(Budget budget);
    List<Budget> getAllBudgets();
}
