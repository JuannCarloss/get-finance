package br.com.getfinance.services.implementation;

import br.com.getfinance.dtos.DefaultPercentageMessage;
import br.com.getfinance.dtos.TotalByMonthDTO;
import br.com.getfinance.enums.InstallmentStatus;
import br.com.getfinance.models.Installment;
import br.com.getfinance.repositories.InstallmentRepository;
import br.com.getfinance.repositories.specifications.InstallmentSpecification;
import br.com.getfinance.services.IInstallmentService;
import br.com.getfinance.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstallmentService implements IInstallmentService {

    private final InstallmentRepository installmentRepository;
    private final IUserService iUserService;

    @Override
    public void create(Installment installment) {
        installmentRepository.save(installment);
    }

    @Override
    @Scheduled(cron = "0 0 1 * * *")
    public void updateStatus() {
        var list = installmentRepository.findAllByInstallmentStatusAndDateBefore(InstallmentStatus.PAGAR.toString(), LocalDate.now());

        list.forEach(installment -> installment.setInstallmentStatus(InstallmentStatus.PAGO));
    }

    @Override
    public List<TotalByMonthDTO> totalAmountByMonth(Long id) {
        var list = installmentRepository.listTotalAmount(LocalDate.now(), id);

        return list.stream()
                .map(TotalByMonthDTO::fromEntity)
                .toList();
    }

    @Override
    public List<Installment> listInstallmentsInThisMonth(Long id) {

        var spec = Specification
                .where(InstallmentSpecification.installmentsInThisMonth())
                .and(InstallmentSpecification.byUserID(id));

        return installmentRepository.findAll(spec);
    }

    @Override
    public boolean checkSalaryUsage(Long userID) {

        var spec = Specification
                .where(InstallmentSpecification
                        .installmentsInThisMonth()
                        .and(InstallmentSpecification.byUserID(userID)));

        var list = installmentRepository.findAll(spec);
        var user = iUserService.byID(userID);

        Double totalAmountInstallments = list
                .stream()
                .mapToDouble(Installment::getAmount)
                .sum();

        double percentage = totalAmountInstallments / user.getAverageSalary() * 100;

        return percentage > 50;
    }

    @Override
    public DefaultPercentageMessage alertSalaryUsage(Long id) {

        if (checkSalaryUsage(id))
            new DefaultPercentageMessage("Cuidado, mais de 50% do seu salário está comprometido com parcelas nos próximos meses");

        return new DefaultPercentageMessage("");
    }
}
