package summer.camp.order_service.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import summer.camp.events.OrderCreatedEvent;
import summer.camp.events.ProductCreatedEvent;
import summer.camp.order_service.entities.Order;
import summer.camp.order_service.entities.Product;
import summer.camp.order_service.repositories.OrderRepo;
import summer.camp.order_service.repositories.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OrderEventsHandler {
    private ProductRepo productRepo;
    private OrderRepo orderRepo;
    @EventHandler
    public void on(ProductCreatedEvent event)
    {
        log.info("**************************************");
        log.info("ProductCreatedEvent received");
        Product product=Product.builder()
                .name(event.getName())
                .id(event.getId())
                .price(event.getPrice())
                .expirationDate(event.getExpirationDate())
                .quantity(event.getQuantity())
                .inventoryId(event.getInventoryId())
                .build();
        productRepo.save(product);
    }
    @EventHandler
    public void on(OrderCreatedEvent event)
    {
        log.info("**************************************");
        log.info("OrderCreatedEvent received");
        Product product = productRepo.findById(event.getProductId()).orElse(null);
        orderRepo.save(Order.builder()
                        .customerName(event.getCustomerName())
                        .id(event.getId())
                        .product(product)
                        .quantity(event.getQuantity())
                        .build());
    }
}
