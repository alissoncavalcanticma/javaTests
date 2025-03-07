package br.com.ctfera.api.services.impl;

import br.com.ctfera.api.domain.User;
import br.com.ctfera.api.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findById(Integer Id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }
}
