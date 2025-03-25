package br.com.ctfera.api.resources;

import br.com.ctfera.api.domain.User;
import br.com.ctfera.api.domain.dto.UserDTO;
import br.com.ctfera.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserResourceTest {

    @InjectMocks
    private UserResource userResource;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    private static final Integer ID         = 1;
    private static final String NAME        = "Alisson C.";
    private static final String EMAIL       = "alisson@alisson.com";
    private static final String PASSWORD    = "123";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); //Inicializando mocks da classe
        startUser();
    }

    @Test
    void findById() {

    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}