package br.com.getfinance.services.implementation;

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
        return userRepository.save(user);
    }

    @Override
    public User byID(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("usuário não existe"));
    }
}
