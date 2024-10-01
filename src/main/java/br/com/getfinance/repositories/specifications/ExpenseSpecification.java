package br.com.getfinance.repositories.specifications;

import br.com.getfinance.models.Expense;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;


public class ExpenseSpecification {

    private ExpenseSpecification(){
        throw new IllegalStateException("Utility Class");
    }

    public static Specification<Expense> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty() || category.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("expenseCategory"), category.toUpperCase());
        };
    }

    public static Specification<Expense> hasUserid(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("userID").get("id"), id);
        };
    }

    public static Specification<Expense> hasTransactionType(String type){
        return (root, query, criteriaBuilder) -> {
            if (type == null || type.isBlank() || type.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("transactionType"), type.toUpperCase());
        };
    }

    public static Specification<Expense> dateBetween(LocalDate start, LocalDate end){
        return (root, query, criteriaBuilder) -> {
            if (start == null || end == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("date"), start, end);
        };
    }
}
