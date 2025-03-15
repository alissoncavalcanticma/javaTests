package br.com.ctfera.api.services.impl;

import br.com.ctfera.api.domain.User;
import br.com.ctfera.api.domain.dto.UserDTO;
import br.com.ctfera.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

//Importação estática
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    private static final Integer ID         = 1;
    private static final String NAME        = "Alisson C.";
    private static final String EMAIL       = "alisson@alisson.com";
    private static final String PASSWORD    = "123";


    @InjectMocks //Annotation para criar uma instância real de testes
    private UserServiceImpl userService;

    @Mock //Annotation para criar um mock real
    private UserRepository userRepository;

    @Mock //Annotation para criar um mock real
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;


    @BeforeEach
    void setUp() {
        //Chamada para iniciar os mocks dessa class com Mockito
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser); //Mockando o retorno de repository.findById();

        User response = userService.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertInstanceOf(Integer.class, response.getId());
        assertEquals(response.getId(), ID);
        assertInstanceOf(String.class, response.getName());
        assertEquals(response.getName(), NAME);
        assertInstanceOf(String.class, response.getEmail());
        assertEquals(response.getEmail(), EMAIL);
        assertInstanceOf(String.class, response.getPassword());
        assertEquals(response.getPassword(), PASSWORD);

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

    @Test
    void findByEmail() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}