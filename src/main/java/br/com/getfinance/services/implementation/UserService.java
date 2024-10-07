package br.com.getfinance.services.implementation;

import br.com.getfinance.exceptions.NotFoundException;
import br.com.getfinance.exceptions.ValidationException;
import br.com.getfinance.models.User;
import br.com.getfinance.repositories.UserRepository;
import br.com.getfinance.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {

        if (userRepository.findByEmail(user.getEmail()) != null){
            throw new ValidationException("Email já está em uso");
        }

        if (userRepository.findByUsername(user.getUsername()) != null){
            throw new ValidationException("Username já está em uso");
        }

        return userRepository.save(user);
    }

    @Override
    public User byID(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("usuário não existe"));
    }
}
