package br.com.getfinance.resources;

import br.com.getfinance.dtos.TotalByMonthDTO;
import br.com.getfinance.services.IInstallmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/total")
    public ResponseEntity<List<TotalByMonthDTO>> listTotal(){
        return ResponseEntity.ok().body(iInstallmentService.totalAmountByMonth());
    }
}
