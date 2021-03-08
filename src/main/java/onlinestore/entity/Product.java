package onlinestore.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Product extends BaseEntity {

    private String name;
    private String category;
    private String manufacturer;
    private String model;
    private String price;
    private String quantity;
    private String image;
    private Status status;
    @ManyToOne
    private ShoppingCart shoppingCart;

    public Product(Long id, String name, String category, String manufacturer, String model, String price, String quantity, String image, Status status) {
        super(id);
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.model = model;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
