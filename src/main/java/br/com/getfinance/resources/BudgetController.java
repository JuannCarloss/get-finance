package br.com.getfinance.resources;

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
    @Operation(description = "list all budgets after actual date")
    @GetMapping("/all")
    public ResponseEntity<List<Budget>> findAll(){
        return ResponseEntity.ok().body(iBudgetService.getAllBudgets());
    }
}
