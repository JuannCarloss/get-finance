package br.com.getfinance.services.implementation;

import br.com.getfinance.models.Budget;
import br.com.getfinance.models.Installment;
import br.com.getfinance.repositories.BudgetRepository;
import br.com.getfinance.services.IBudgetService;
import br.com.getfinance.services.IInstallmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService implements IBudgetService {

    private final BudgetRepository budgetRepository;
    private final IInstallmentService iInstallmentService;

    @Override
    public Budget create(Budget entity) {
        var budget = budgetRepository.save(entity);

        for (int i = 1; i <= budget.getInstallmentQuantity(); i++){
            var amount = budget.getTotalAmount() / budget.getInstallmentQuantity();
            var date = LocalDate.now().plusMonths(i);
            var installment = new Installment(budget, amount, date);

            iInstallmentService.create(installment);
        }
        return budget;
    }

    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }
}
