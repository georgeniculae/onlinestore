package onlinestore.transformer;

import onlinestore.dto.ShoppingCartDTO;
import onlinestore.entity.ShoppingCart;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartTransformer {

    public ShoppingCartDTO transformToDTO(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        BeanUtils.copyProperties(shoppingCart, shoppingCartDTO);
        return shoppingCartDTO;
    }

    public ShoppingCart transformFromDTO(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        return shoppingCart;
    }
}
