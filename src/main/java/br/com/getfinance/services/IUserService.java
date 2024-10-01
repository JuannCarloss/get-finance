package br.com.getfinance.services;

import br.com.getfinance.models.User;

public interface IUserService {

    User save(User user);
    User byID(Long id);
}
