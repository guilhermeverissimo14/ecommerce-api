package shopping.ecommerce.products.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shopping.ecommerce.products.model.Product;
import shopping.ecommerce.products.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductByCode(Long code) {
        return productRepository.findById(code);
    }
}