package br.com.getfinance.resources;

import br.com.getfinance.models.User;
import br.com.getfinance.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;


    @Tag(name = "USER")
    @Operation(description = "create an user")
    @PostMapping("/create")
    public ResponseEntity<User> post(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }
}
