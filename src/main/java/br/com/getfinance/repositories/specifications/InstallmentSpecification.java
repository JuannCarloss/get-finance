package br.com.getfinance.repositories.specifications;

import br.com.getfinance.models.Installment;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class InstallmentSpecification {

    private InstallmentSpecification(){
        throw new IllegalStateException("Utility Class");
    }

    public static Specification<Installment> byCategory(String category){
        return (root, query, criteriaBuilder) -> {

            if (category == null || category.isEmpty() || category.isBlank()){
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("category"), category.toUpperCase());
        };
    }

    public static Specification<Installment> installmentsInThisMonth(){
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.between(root.get("date"), LocalDate.now().withDayOfMonth(1), LocalDate.now().withDayOfMonth(31));
    }

    public static Specification<Installment> byUserID(Long id){
        return (root, query, criteriaBuilder) -> {
            if (id == null || id.describeConstable().isEmpty()){
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("userID"), id);
        };
    }
}
