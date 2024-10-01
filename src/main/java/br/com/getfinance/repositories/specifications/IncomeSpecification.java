package br.com.getfinance.repositories.specifications;

import br.com.getfinance.models.Income;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class IncomeSpecification {

    private IncomeSpecification(){
        throw new IllegalStateException("Utility Class");
    }

    public static Specification<Income> hasUserID(Long user){
        return (root, query, criteriaBuilder) -> {
          if (user == null){
              return criteriaBuilder.conjunction();
          }
          return criteriaBuilder.equal(root.get("user").get("id"), user);
        };
    }

    public static Specification<Income> hasSourceCategory(String category){
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty() || category.isBlank()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("sourceTypes"), category.toUpperCase());
        };
    }

    public static Specification<Income> dateBetween(LocalDate start, LocalDate end) {
        return (root, query, criteriaBuilder) -> {
            if (start == null || end == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("date"), start, end);
        };
    }
}
