package br.com.ctfera.api.services;

import br.com.ctfera.api.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer Id);
    List<User> findAll();
}
