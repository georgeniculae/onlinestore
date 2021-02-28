package onlinestore.mvccontroller;

import onlinestore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShoppingCartMVCController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartMVCController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

}
