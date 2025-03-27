package br.com.ctfera.api.services.impl;

import br.com.ctfera.api.domain.User;
import br.com.ctfera.api.repository.UserRepository;
import br.com.ctfera.api.services.UserService;
import br.com.ctfera.api.services.exceptions.DataIntegrityViolationException;
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
        findByEmail(obj);
        return userRepository.save(obj);
    }

    @Override
    public User update(User user){
        findByEmail(user);
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id){
        findById(id);
        userRepository.deleteById(id);
    }


    //======== Métodos auxiliares ============
    public void findByEmail(User obj){
        Optional<User> user = userRepository.findByEmail(obj.getEmail());
        if(user.isPresent() && !user.get().getId().equals(obj.getId())) {
           throw new DataIntegrityViolationException("Email já existente na base");
        }
    }
}
