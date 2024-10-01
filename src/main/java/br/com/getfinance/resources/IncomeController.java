package br.com.getfinance.resources;

import br.com.getfinance.models.Income;
import br.com.getfinance.services.IIncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/income")
@RequiredArgsConstructor
public class IncomeController {

    private final IIncomeService iIncomeService;

    @Tag(name = "INCOME")
    @Operation(description = "create an income")
    @PostMapping("/create")
    public ResponseEntity<Income> post(@RequestBody Income income){
        return ResponseEntity.status(HttpStatus.CREATED).body(iIncomeService.create(income));
    }

    @Tag(name = "INCOME")
    @Operation(description = "list all incomes")
    @GetMapping("/all")
    public ResponseEntity<List<Income>> filterAndListAll(@RequestParam Long id,
                                                         @RequestParam(required = false) String category,
                                                         @RequestParam(required = false) LocalDate start,
                                                         @RequestParam(required = false) LocalDate end){
        return ResponseEntity.ok().body(iIncomeService.filterAndGetAllIncomes(id, category, start, end));
    }

}
