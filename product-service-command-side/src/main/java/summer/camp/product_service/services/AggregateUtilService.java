package summer.camp.product_service.services;

public interface AggregateUtilService {
    boolean InventoryExists(String inventoryId);
    boolean productExists(String productId);
}
