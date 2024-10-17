package summer.camp.product_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import summer.camp.product_service.entities.Product;

public interface ProductRepo extends JpaRepository<Product,String> {
}
