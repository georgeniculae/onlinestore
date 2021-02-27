package onlinestore.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Product extends BaseEntity {

    private String name;
    private String price;
    private String quantity;
    private String image;
    private Status status;
    @ManyToOne
    private ShoppingCart shoppingCart;

    public Product(String name, String price, String quantity, String image, Status status) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.status = status;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
