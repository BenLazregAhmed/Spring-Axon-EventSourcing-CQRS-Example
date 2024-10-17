package summer.camp.product_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import summer.camp.product_service.entities.Inventory;
import summer.camp.product_service.entities.Product;

public interface InventoryRepo extends JpaRepository<Inventory,String> {
}
