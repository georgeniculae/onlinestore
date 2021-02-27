package onlinestore.service;

import onlinestore.entity.User;
import onlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

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
            users.add(new User("admin", "admin", "ROLE_ADMIN"));
        } else if (!userRepository.findByUsername("support").isPresent()) {
            users.add(new User("support", "support", "ROLE_SUPPORT"));
        } else if (!userRepository.findByUsername("customer").isPresent()) {
            users.add(new User("customer", "customer", "ROLE_CUSTOMER"));
        }
        userRepository.saveAll(users);
    }

    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public Optional<User> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user;
    }

    public void findUserById(Long id) {
        userRepository.findById(id);
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

    public void deleteUserById(User user, Long id) {
        if (!user.getType().equals("ROLE_ADMIN") || !user.getType().equals("ROLE_SUPPORT")) {
            userRepository.deleteById(id);
        }
    }

    public void userCount() {
        userRepository.count();
    }
}
