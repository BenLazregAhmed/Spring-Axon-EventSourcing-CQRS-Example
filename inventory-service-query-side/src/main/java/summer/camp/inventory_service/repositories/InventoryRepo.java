package summer.camp.inventory_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import summer.camp.inventory_service.entities.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory,String> {
}
