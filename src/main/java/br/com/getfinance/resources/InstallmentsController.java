package br.com.getfinance.resources;

import br.com.getfinance.dtos.DefaultPercentageMessage;
import br.com.getfinance.dtos.TotalByMonthDTO;
import br.com.getfinance.models.Installment;
import br.com.getfinance.services.IInstallmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("installments")
public class InstallmentsController {

    private final IInstallmentService iInstallmentService;

    @Tag(name = "INSTALLMENT")
    @Operation(description = "list total amount of installments by month")
    @GetMapping("/total/{id}")
    public ResponseEntity<List<TotalByMonthDTO>> listTotal(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(iInstallmentService.totalAmountByMonth(id));
    }

    @Tag(name = "INSTALLMENT")
    @Operation(description = "list all installments by actual month")
    @GetMapping("/month/{id}")
    public ResponseEntity<List<Installment>> allByMonth(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(iInstallmentService.listInstallmentsInThisMonth(id));
    }

    @Tag(name = "INSTALLMENT")
    @Operation(description = "check if the salary usage its above 50%")
    @GetMapping("/percentage/{id}")
    public ResponseEntity<DefaultPercentageMessage> alertSalaryUsage(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(iInstallmentService.alertSalaryUsage(id));
    }
}
