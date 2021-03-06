package onlinestore.mvccontroller;

import javassist.NotFoundException;
import onlinestore.dto.CustomerDTO;
import onlinestore.entity.Product;
import onlinestore.entity.User;
import onlinestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserMVCController {

    private final UserService userService;

    @Autowired
    public UserMVCController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public String users(Model model) {
        model.addAttribute("users", this.userService.findAllUsers());
        return "users";
    }

    @GetMapping(path = "/user/registration")
    public String showRegistration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allUsers", this.userService.findAllUsers());
        return "new-user";
    }

    @GetMapping(path = "/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "new-user";
    }

    @PostMapping(path = "/user/add")
    public String addUser(@PathVariable("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-user";
        } else {
            userService.saveUser(user);
            return "redirect:/users";
        }
    }

    @PostMapping(path = "/user/update")
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-user";
        } else {
            this.userService.saveUser(user);
            return "redirect:/users";
        }
    }

    @GetMapping(path = "/user/edit/{id}")
    public String showUpdateUserPage(@PathVariable("id") Long id, Model model) throws NotFoundException {
        model.addAttribute("user", this.userService.findUserById(id));
        return "edit-user";
    }

    @GetMapping(path = "/user/delete/{id}")
    public String deleteEmployeeById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping(path = "/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping(path = "/user/register")
    public String registerUser(@ModelAttribute("userRegister") @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
        Optional<User> userOptional = userService.findUserByUsername(customerDTO.getUsername());
        if (userOptional.isPresent()) {
            bindingResult.rejectValue("username", null, "Username already exists!");
        }
        if (!customerDTO.getPassword().equals(customerDTO.getConfirmPassword())) {
            bindingResult.rejectValue("password", null, "Passwords do not match!");
        }
        if (bindingResult.hasErrors()) {
            return "new-user";
        }
        userService.registerCustomer(customerDTO);
        return "login";
    }
}
