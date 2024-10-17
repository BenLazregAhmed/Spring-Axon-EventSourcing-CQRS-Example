package summer.camp.product_service.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import summer.camp.product_service.entities.Inventory;
import summer.camp.product_service.entities.Product;
import summer.camp.product_service.repositories.InventoryRepo;
import summer.camp.product_service.repositories.ProductRepo;

@Service
@AllArgsConstructor
public class AggregateUtilServiceImpl implements AggregateUtilService {

    private ProductRepo productRepo;
    private InventoryRepo inventoryRepo;
    @Override
    public boolean InventoryExists(String inventoryId) {
        Inventory inventory=inventoryRepo.findById(inventoryId).orElse(null);
        return inventory!=null;
    }

    @Override
    public boolean productExists(String productId) {
        Product product=productRepo.findById(productId).orElse(null);
        return product!=null;
    }
}
