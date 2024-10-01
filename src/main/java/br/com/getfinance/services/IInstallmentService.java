package br.com.getfinance.services;

import br.com.getfinance.dtos.TotalByMonthDTO;
import br.com.getfinance.models.Installment;

import java.util.List;

public interface IInstallmentService {

    void create(Installment installment);
    void updateStatus();
    List<TotalByMonthDTO> totalAmountByMonth();
}
