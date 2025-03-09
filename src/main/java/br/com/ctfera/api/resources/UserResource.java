package br.com.ctfera.api.resources;

import br.com.ctfera.api.domain.User;
import br.com.ctfera.api.domain.dto.UserDTO;
import br.com.ctfera.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private ModelMapper mapper;

    //Usando a interface o Spring identifica a implementação da mesma. Pode ser usado os 2.
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        //return ResponseEntity.ok().body(userService.findById(id));
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        //List<User> list = userService.findAll();
        List<UserDTO> listDTO = userService.findAll()
                .stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());
        //List<UserDTO> listDTO = list.stream().map(x -> mapper.map(x, UserDTO.class)).toList(); //Também poderia ser assim
        return ResponseEntity.ok().body(listDTO);
    }
}
