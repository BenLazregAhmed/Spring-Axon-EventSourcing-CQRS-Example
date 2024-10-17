package summer.camp.order_service.services;

public interface OrderAggregateUtilService {
    boolean productExists(String productId);
    boolean quantityAvailable(int quantity,String productId);
}
