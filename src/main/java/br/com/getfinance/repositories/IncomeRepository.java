package br.com.getfinance.repositories;

import br.com.getfinance.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IncomeRepository extends JpaRepository<Income, Long>, JpaSpecificationExecutor<Income> {
}