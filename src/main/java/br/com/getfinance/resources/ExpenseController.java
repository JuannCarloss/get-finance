package br.com.getfinance.resources;

import br.com.getfinance.dtos.MonthlyExpenseDTO;
import br.com.getfinance.models.Expense;
import br.com.getfinance.services.IExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final IExpenseService service;

    @Tag(name = "EXPENSE")
    @Operation(description = "create an expense")
    @PostMapping("/create")
    public ResponseEntity<Expense> post(@RequestBody Expense expense){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(expense));
    }

    @Tag(name = "EXPENSE")
    @Operation(description = "list all expenses by user id")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<Expense>> getAllByUser(@RequestParam(required = false)String category,
                                                      @PathVariable("id")Long userID,
                                                      @RequestParam(required = false)String type,
                                                      @RequestParam(required = false)LocalDate start,
                                                      @RequestParam(required = false)LocalDate end){
        return ResponseEntity.status(HttpStatus.OK).body(service.filterAndListAllExpenses(userID,category, type, start, end));
    }

    @Tag(name = "EXPENSE")
    @Operation(description = "list all monthly expenses")
    @GetMapping("/monthly/{userid}")
    public ResponseEntity<List<MonthlyExpenseDTO>> getAllMonthlyExpenses(@PathVariable("userid")Long id){
        return ResponseEntity.ok().body(service.findAllMonthlyExpenses(id));
    }
}
