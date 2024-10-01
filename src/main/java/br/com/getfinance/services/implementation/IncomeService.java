package br.com.getfinance.services.implementation;

import br.com.getfinance.models.Income;
import br.com.getfinance.repositories.IncomeRepository;
import br.com.getfinance.repositories.specifications.IncomeSpecification;
import br.com.getfinance.services.IIncomeService;
import br.com.getfinance.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService implements IIncomeService {

    private final IncomeRepository incomeRepository;
    private final IUserService iUserService;

    @Override
    public Income create(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public List<Income> filterAndGetAllIncomes(Long id,
                                               String category,
                                               LocalDate start,
                                               LocalDate end) {

        var spec = Specification
                .where(IncomeSpecification.hasUserID(id)
                .and(IncomeSpecification.hasSourceCategory(category))
                .and(IncomeSpecification.dateBetween(start, end)));

        return incomeRepository.findAll(spec);
    }
}
