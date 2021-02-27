package onlinestore.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Map;

@Entity
public class ShoppingCart extends BaseEntity {

    @OneToMany(mappedBy = "products")
    private Map<Integer, Product> products;

    public ShoppingCart(Map<Integer, Product> products) {
        this.products = products;
    }

    public ShoppingCart() {
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }
}
