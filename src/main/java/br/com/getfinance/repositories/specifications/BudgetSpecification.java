package br.com.getfinance.repositories.specifications;

import br.com.getfinance.models.Budget;
import org.springframework.data.jpa.domain.Specification;


public class BudgetSpecification {

    private BudgetSpecification(){
        throw new IllegalStateException("Utility Class");
    }

    public static Specification<Budget> hasUser(Long userID){
        return (root, query, criteriaBuilder) -> {

            if (userID== null){
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("user").get("id"), userID);
        };
    }
}
