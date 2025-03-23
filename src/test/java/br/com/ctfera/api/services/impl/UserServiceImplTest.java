package br.com.ctfera.api.services.impl;

import br.com.ctfera.api.domain.User;
import br.com.ctfera.api.domain.dto.UserDTO;
import br.com.ctfera.api.repository.UserRepository;
import br.com.ctfera.api.services.exceptions.DataIntegrityViolationException;
import br.com.ctfera.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try{
            userService.findById(ID);
        }catch(Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenResultAnListOfUsersr() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> response = userService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userService.create(user);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());

    }

    @Test
    void whenCreateThenReturnAnDataViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            optionalUser.get().setId(ID);
            userService.create(user);
        } catch (Exception e) {
           assertEquals(DataIntegrityViolationException.class, e.getClass());
        }
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