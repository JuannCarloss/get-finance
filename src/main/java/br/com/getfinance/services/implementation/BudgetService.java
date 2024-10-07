package br.com.getfinance.services.implementation;

import br.com.getfinance.dtos.BudgetDTO;
import br.com.getfinance.exceptions.NotFoundException;
import br.com.getfinance.models.Budget;
import br.com.getfinance.models.Installment;
import br.com.getfinance.repositories.BudgetRepository;
import br.com.getfinance.repositories.specifications.BudgetSpecification;
import br.com.getfinance.services.IBudgetService;
import br.com.getfinance.services.IInstallmentService;
import br.com.getfinance.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService implements IBudgetService {

    private final BudgetRepository budgetRepository;
    private final IInstallmentService iInstallmentService;
    private final IUserService iUserService;
    private final MailService iMailService;

    @Override
    public Budget create(Budget entity) {
        var budget = budgetRepository.save(entity);

        for (int i = 1; i <= budget.getInstallmentQuantity(); i++){
            var amount = budget.getTotalAmount() / budget.getInstallmentQuantity();
            var date = LocalDate.now().plusMonths(i);
            var installment = new Installment(budget, budget.getUser().getId(), amount, date, entity.getCategory());

            iInstallmentService.create(installment);
        }

        if (iInstallmentService.checkSalaryUsage(budget.getUser().getId())) {
            var user = iUserService.byID(budget.getUser().getId());
            iMailService.sendMail("Alert Email", "Cuidado! Suas parcelas mensais somam mais que 50% do seu salário", user.getEmail());
        }

        return budget;
    }

    @Override
    public Budget getBudgetWithInstallments(Long id) {
        return budgetRepository.findById(id).orElseThrow(() -> new NotFoundException("Orçamento não encontrado"));
    }

    @Override
    public List<BudgetDTO> findAllBudgetsByUserID(Long id) {
        var spec = Specification
                .where(BudgetSpecification
                        .hasUser(id));

        return budgetRepository.findAll(spec)
                .stream()
                .map(BudgetDTO::fromEntity)
                .toList();
    }
}
