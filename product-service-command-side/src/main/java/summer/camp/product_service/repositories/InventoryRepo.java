package summer.camp.product_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import summer.camp.product_service.entities.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory,String> {
}
