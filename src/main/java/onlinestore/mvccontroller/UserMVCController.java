package onlinestore.mvccontroller;

import onlinestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
