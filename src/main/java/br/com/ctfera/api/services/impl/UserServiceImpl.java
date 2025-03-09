package br.com.ctfera.api.services.impl;

import br.com.ctfera.api.domain.User;
import br.com.ctfera.api.repository.UserRepository;
import br.com.ctfera.api.services.UserService;
import br.com.ctfera.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer Id) {
        Optional<User> obj = userRepository.findById(Id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User obj){
        return userRepository.save(obj);
    }
}
