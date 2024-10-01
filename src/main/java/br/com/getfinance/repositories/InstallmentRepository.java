package br.com.getfinance.repositories;

import br.com.getfinance.models.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {

    List<Installment> findAllByInstallmentStatusAndDateBefore(String status, LocalDate date);

    @Query("""
            SELECT SUM(i.amount) AS total, DATE_TRUNC('month', i.date) as mes
            FROM tb_installments i
            WHERE i.installmentStatus = 'PAGAR'
            AND i.date >= :actualdate
            GROUP BY DATE_TRUNC('month', date)
            ORDER BY mes""")
    List<Object[]> listTotalAmount(LocalDate actualdate);
}