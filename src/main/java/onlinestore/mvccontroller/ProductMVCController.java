package onlinestore.mvccontroller;

import onlinestore.entity.Product;
import onlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
        return "products";
    }

    @PostMapping(path = "/product/{id}")
    public String createProduct(@PathVariable("id") @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-product";
        } else {
            productService.createProduct(product);
            return "redirect:/products";
        }
    }
}
