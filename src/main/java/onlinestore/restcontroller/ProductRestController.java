package onlinestore.restcontroller;

import javassist.NotFoundException;
import onlinestore.dto.ProductDTO;
import onlinestore.entity.Product;
import onlinestore.service.ProductService;
import onlinestore.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/product")
@CrossOrigin(origins = "*")
public class ProductRestController {

    private final ProductService productService;
    private final ProductTransformer productTransformer;

    @Autowired
    public ProductRestController(ProductService productService, ProductTransformer productTransformer) {
        this.productService = productService;
        this.productTransformer = productTransformer;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestAttribute ProductDTO productDTO) {
        Product product = productTransformer.transformFromDTO(productDTO);
        Product savedProduct = productService.createProduct(product);
        ProductDTO savedProductDTO = productTransformer.transformToDTO(savedProduct);
        return ResponseEntity.ok(savedProductDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> displayProduct(@PathVariable("id") Long id) throws NotFoundException {
        Product product = productService.findProductById(id);
        ProductDTO productDTO = productTransformer.transformToDTO(product);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ProductDTO>> displayProducts() {
        List<Product> products = productService.findAllProducts();
        List<ProductDTO> productsDTO = new ArrayList<>();

        for (Product product : products) {
            productsDTO.add(productTransformer.transformToDTO(product));
        }
        return ResponseEntity.ok(productsDTO);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        Product product = productTransformer.transformFromDTO(productDTO);
        Product savedProduct = productService.createProduct(product);
        ProductDTO savedProductDTO = productTransformer.transformToDTO(savedProduct);
        return ResponseEntity.ok(savedProductDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
