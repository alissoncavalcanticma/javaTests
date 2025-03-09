package br.com.ctfera.api.services;

import br.com.ctfera.api.domain.User;
import br.com.ctfera.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer Id);
    List<User> findAll();
    //User create(UserDTO obj);
    User create(User user);
}
