package onlinestore.dto;

import onlinestore.entity.Product;

import java.util.Map;

public class ShoppingCartDTO extends BaseEntityDTO {

    private Map<Integer, onlinestore.entity.Product> products;

    public ShoppingCartDTO(Map<Integer, onlinestore.entity.Product> products) {
        this.products = products;
    }

    public ShoppingCartDTO() {
    }

    public Map<Integer, onlinestore.entity.Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }
}
