package onlinestore.restcontroller;

import onlinestore.dto.ShoppingCartDTO;
import onlinestore.entity.ShoppingCart;
import onlinestore.service.ShoppingCartService;
import onlinestore.transformer.ShoppingCartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/shoppingCart")
@CrossOrigin("*")
public class ShoppingCartRestController {

    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartTransformer shoppingCartTransformer;

    @Autowired
    public ShoppingCartRestController(ShoppingCartService shoppingCartService, ShoppingCartTransformer shoppingCartTransformer) {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartTransformer = shoppingCartTransformer;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ShoppingCartDTO>> viewShoppingCart() {
        List<ShoppingCart> shoppingCart = shoppingCartService.findShoppingCart();
        List<ShoppingCartDTO> shoppingCartDTO = new ArrayList<>();

        for (ShoppingCart element : shoppingCart) {
            shoppingCartDTO.add(shoppingCartTransformer.transformToDTO(element));
        }
        return ResponseEntity.ok(shoppingCartDTO);
    }

    @PutMapping
    public ResponseEntity<ShoppingCartDTO> updateShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = shoppingCartTransformer.transformFromDTO(shoppingCartDTO);
        ShoppingCart savedSoppingCart = shoppingCartService.saveShoppingCart(shoppingCart);
        ShoppingCartDTO savedShoppingCartDTO = shoppingCartTransformer.transformToDTO(savedSoppingCart);
        return ResponseEntity.ok(savedShoppingCartDTO);
    }
}
