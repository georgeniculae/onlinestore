package onlinestore.service;

import javassist.NotFoundException;
import onlinestore.dto.CustomerDTO;
import onlinestore.entity.Customer;
import onlinestore.entity.User;
import onlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void onInit() {
        createUsers();
    }

    public void createUsers() {
        List<User> users = new ArrayList<>();
        if (!userRepository.findByUsername("admin").isPresent()) {
            users.add(new User("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN"));
        } else if (!userRepository.findByUsername("support").isPresent()) {
            users.add(new User("support", passwordEncoder.encode("support"), "ROLE_SUPPORT"));
        } else if (!userRepository.findByUsername("customer").isPresent()) {
            users.add(new User("customer", passwordEncoder.encode("customer"), "ROLE_CUSTOMER"));
        }
        userRepository.saveAll(users);
    }

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public Optional<User> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user;
    }

    public User findUserById(Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException("User not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    optionalUser.get().getUsername(),
                    optionalUser.get().getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority(optionalUser.get().getType())));
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    public Optional<User> findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public Long userCount() {
        return userRepository.count();
    }

    public Customer registerCustomer(CustomerDTO customerDTO) {
        Customer user = new Customer();
        user.setUsername(customerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        user.setFirstName(customerDTO.getFirstName());
        user.setLastName(customerDTO.getLastName());
        user.setEmail(customerDTO.getEmail());
        user.setAddress(customerDTO.getAddress());
        user.setType("ROLE_CUSTOMER");
        return userRepository.save(user);
    }
}
