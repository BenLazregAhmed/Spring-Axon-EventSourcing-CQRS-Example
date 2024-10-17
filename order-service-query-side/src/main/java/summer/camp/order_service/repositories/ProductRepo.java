package summer.camp.order_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import summer.camp.order_service.entities.Product;

public interface ProductRepo extends JpaRepository<Product,String> {
}
