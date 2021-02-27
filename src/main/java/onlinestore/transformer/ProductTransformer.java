package onlinestore.transformer;

import onlinestore.dto.ProductDTO;
import onlinestore.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductTransformer {

    public ProductDTO transformToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }

    public Product transformFromDTO(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
}
