package br.com.getfinance.services.implementation;

import br.com.getfinance.dtos.TotalByMonthDTO;
import br.com.getfinance.enums.InstallmentStatus;
import br.com.getfinance.models.Installment;
import br.com.getfinance.repositories.InstallmentRepository;
import br.com.getfinance.services.IInstallmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstallmentService implements IInstallmentService {

    private final InstallmentRepository installmentRepository;

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
    public List<TotalByMonthDTO> totalAmountByMonth() {
        var list = installmentRepository.listTotalAmount(LocalDate.now());

        return list.stream()
                .map(TotalByMonthDTO::fromEntity)
                .toList();
    }
}
