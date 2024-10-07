package br.com.getfinance.services;

import br.com.getfinance.dtos.BudgetDTO;
import br.com.getfinance.models.Budget;

import java.util.List;


public interface IBudgetService {

    Budget create(Budget budget);
    Budget getBudgetWithInstallments(Long id);
    List<BudgetDTO> findAllBudgetsByUserID(Long id);
}
