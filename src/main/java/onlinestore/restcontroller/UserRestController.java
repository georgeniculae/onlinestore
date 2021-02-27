package onlinestore.restcontroller;

import javassist.NotFoundException;
import onlinestore.dto.UserDTO;
import onlinestore.entity.User;
import onlinestore.service.UserService;
import onlinestore.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "*")
public class UserRestController {

    private final UserService userService;
    private final UserTransformer userTransformer;

    @Autowired
    public UserRestController(UserService userService, UserTransformer userTransformer) {
        this.userService = userService;
        this.userTransformer = userTransformer;
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestAttribute UserDTO userDTO) {
        User user = userTransformer.transformFromDTO(userDTO);
        User savedUser = userService.saveUser(user);
        UserDTO savedUserDTO = userTransformer.transformToDTO(savedUser);
        return ResponseEntity.ok(savedUserDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> displayProduct(@PathVariable("id") Long id) throws NotFoundException {
        User user = userService.findUserById(id);
        UserDTO userDTO = userTransformer.transformToDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<UserDTO>> displayProducts() {
        List<User> users = userService.findAllUsers();
        List<UserDTO> usersDTO = new ArrayList<>();

        for (User user : users) {
            usersDTO.add(userTransformer.transformToDTO(user));
        }
        return ResponseEntity.ok(usersDTO);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateProduct(@RequestBody UserDTO userDTO) {
        User user = userTransformer.transformFromDTO(userDTO);
        User savedUser = userService.saveUser(user);
        UserDTO savedUserDTO = userTransformer.transformToDTO(savedUser);
        return ResponseEntity.ok(savedUserDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserDTO> deleteProduct(@PathVariable("id") Long id, UserDTO userDTO) {
        if (!userDTO.getType().equals("ROLE_ADMIN") || !userDTO.getType().equals("ROLE_SUPPORT")) {
            userService.deleteUserById(id);
        }
        return ResponseEntity.noContent().build();
    }
}
