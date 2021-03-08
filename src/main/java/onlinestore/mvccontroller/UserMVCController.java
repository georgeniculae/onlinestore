package onlinestore.mvccontroller;

import onlinestore.entity.Product;
import onlinestore.entity.User;
import onlinestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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

    @PostMapping(path = "/user/{id}")
    public String createProduct(@PathVariable("id") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-user";
        } else {
            userService.saveUser(user);
            return "redirect:/users";
        }
    }
}
