package br.com.getfinance.services.implementation;

import br.com.getfinance.dtos.MonthlyExpenseDTO;
import br.com.getfinance.models.Expense;
import br.com.getfinance.repositories.ExpenseRepository;
import br.com.getfinance.repositories.specifications.ExpenseSpecification;
import br.com.getfinance.services.IExpenseService;
import br.com.getfinance.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService implements IExpenseService {

    private final ExpenseRepository repository;
    private final IUserService iUserService;


    @Override
    public Expense save(Expense expense) {
        return repository.save(expense);
    }

    @Override
    public List<Expense> filterAndListAllExpenses(Long userID,
                                                  String category,
                                                  String type,
                                                  LocalDate start,
                                                  LocalDate end) {

        var spec = Specification
                .where(ExpenseSpecification.hasUserid(userID)
                .and(ExpenseSpecification.hasCategory(category)
                .and(ExpenseSpecification.hasTransactionType(type)))
                .and(ExpenseSpecification.dateBetween(start, end)));

        return repository.findAll(spec);
    }

    @Override
    public List<MonthlyExpenseDTO> findAllMonthlyExpenses(Long id) {
        var results = repository.getAllMonthlyExpenses(id);

       return results.stream()
               .map(MonthlyExpenseDTO::fromEntity)
               .toList();
    }
}
