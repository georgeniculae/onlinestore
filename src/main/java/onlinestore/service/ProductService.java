package onlinestore.service;

import javassist.NotFoundException;
import onlinestore.entity.Product;
import onlinestore.entity.Status;
import onlinestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void onInit() {
        defaultProducts();
    }

    private void defaultProducts() {
        List<Product> products = new ArrayList<>();
        if (!productRepository.findProductByDescription("Nike Air 360 Shoes").isPresent()) {
            Product nikeAir = new Product("Nike Air 360 Shoes", "shoes", "Nike", "Air 360", "$150", 100, "", Status.AVAILABLE);
        }
        productRepository.saveAll(products);
    }

    public List<Product> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product findProductById(Long id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return product;
        } else {
            throw new NotFoundException("Product not found");
        }
    }

    public Product findProductByDescription(String description) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findProductByDescription(description);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return product;
        } else {
            throw new NotFoundException("No such product");
        }
    }

    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public void countProducts() {
        productRepository.count();
    }
}
