package onlinestore.mvccontroller;

import onlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductMVCController {

    private final ProductService productService;

    @Autowired
    public ProductMVCController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/products")
    public String showProducts(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        return "index";
    }
}
