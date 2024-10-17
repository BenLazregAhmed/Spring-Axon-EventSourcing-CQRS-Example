package summer.camp.inventory_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import summer.camp.inventory_service.entities.Inventory;
import summer.camp.inventory_service.entities.Product;

public interface ProductRepo extends JpaRepository<Product,String> {
}
