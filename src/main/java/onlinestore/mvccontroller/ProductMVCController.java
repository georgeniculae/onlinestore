package onlinestore.mvccontroller;

import onlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductMVCController {

    private final ProductService productService;

    @Autowired
    public ProductMVCController(ProductService productService) {
        this.productService = productService;
    }

    
}
