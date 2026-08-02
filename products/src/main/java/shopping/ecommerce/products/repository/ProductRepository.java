package shopping.ecommerce.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.ecommerce.products.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
