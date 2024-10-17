package summer.camp.inventory_service.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import summer.camp.events.InventoryCreatedEvent;
import summer.camp.events.ProductCreatedEvent;
import summer.camp.events.ProductDeletedEvent;
import summer.camp.events.ProductUpdatedEvent;
import summer.camp.inventory_service.entities.Inventory;
import summer.camp.inventory_service.entities.Product;
import summer.camp.inventory_service.repositories.InventoryRepo;
import summer.camp.inventory_service.repositories.ProductRepo;

import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class InventoryEventHandler {
    private ProductRepo productRepo;
    private InventoryRepo inventoryRepo;

    @EventHandler
    public void on(InventoryCreatedEvent event)
    {
        log.info("**************************************");
        log.info("InventoryCreatedEvent received");
        inventoryRepo.save(
                Inventory.builder()
                        .id(event.getId())
                        .creationDate(event.getCreationDate())
                        .build()
        );
    }

    @EventHandler
    public void on(ProductCreatedEvent event)
    {
        log.info("**************************************");
        log.info("ProductCreatedEvent received");
        Inventory inventory=inventoryRepo.findById(event.getInventoryId()).orElse(null);
        if (inventory==null)
            return;
        Product product=Product.builder()
                .id(event.getId())
                .name(event.getName())
                .price(event.getPrice())
                .expirationDate(event.getExpirationDate())
                .quantity(event.getQuantity())
                .inventory(inventory)
                .build();
        List<Product> products = inventory.getProducts();
        products.add(product);
        inventory.setProducts(products);
        productRepo.save(product);
    }

    @EventHandler
    public void on(ProductDeletedEvent event)
    {
        log.info("**************************************");
        log.info("ProductDeletedEvent received");
        Product product=productRepo.findById(event.getId()).orElse(null);
        Inventory inventory=product.getInventory();
        List<Product> products = inventory.getProducts();
        products.remove(product);
        inventory.setProducts(products);
        inventoryRepo.save(inventory);
        product.setInventory(null);
        productRepo.delete(product);
    }

    @EventHandler void on(ProductUpdatedEvent event)
    {
        log.info("**************************************");
        log.info("ProductUpdatedEvent received");
        Inventory inventory=inventoryRepo.findById(event.getInventoryId()).orElse(null);
        Product product=productRepo.findById(event.getId()).orElse(null);
        List<Product> products = inventory.getProducts();
        products.add(product);
        inventory.setProducts(products);
        product.setName(event.getName());
        product.setPrice(event.getPrice());
        product.setExpirationDate(event.getExpirationDate());
        product.setQuantity(event.getQuantity());
        product.setInventory(inventory);
        productRepo.save(product);
    }
}
