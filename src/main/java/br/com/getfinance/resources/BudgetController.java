package br.com.getfinance.resources;

import br.com.getfinance.dtos.BudgetDTO;
import br.com.getfinance.models.Budget;
import br.com.getfinance.services.IBudgetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final IBudgetService iBudgetService;

    @Tag(name = "BUDGET")
    @Operation(description = "create a budget")
    @PostMapping("/create")
    public ResponseEntity<Budget> post(@RequestBody Budget budget){
        return ResponseEntity.status(HttpStatus.CREATED).body(iBudgetService.create(budget));
    }

    @Tag(name = "BUDGET")
    @Operation(description = "Find budget by id with installments")
    @GetMapping("/{id}")
    public ResponseEntity<Budget> findByID(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(iBudgetService.getBudgetWithInstallments(id));
    }

    @Tag(name = "BUDGET")
    @Operation(description = "List all budgets by user id")
    @GetMapping("/user/{id}")
    public ResponseEntity<List<BudgetDTO>> findAll(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(iBudgetService.findAllBudgetsByUserID(id));
    }

}
