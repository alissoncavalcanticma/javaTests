package br.com.ctfera.api.resources;

import br.com.ctfera.api.domain.User;
import br.com.ctfera.api.domain.dto.UserDTO;
import br.com.ctfera.api.services.UserService;
import jakarta.servlet.Servlet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj) {
        User newObj = userService.create(mapper.map(obj, User.class));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO obj){
        obj.setId(id);
        User newObj = userService.update(mapper.map(obj, User.class));
        return ResponseEntity.ok().body(mapper.map(newObj, UserDTO.class));
    }

}
