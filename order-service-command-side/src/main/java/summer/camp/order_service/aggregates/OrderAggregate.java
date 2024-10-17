package summer.camp.order_service.aggregates;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import summer.camp.commands.CreateOrderCommand;
import summer.camp.events.OrderCreatedEvent;
import summer.camp.exceptions.InsufficientQuantityException;
import summer.camp.exceptions.ProductNotFoundException;
import summer.camp.order_service.services.OrderAggregateUtilService;

@Aggregate
@NoArgsConstructor
@Slf4j
public class OrderAggregate {
    @AggregateIdentifier
    private String id;
    private int quantity;
    private String customerName;
    private String productId;
    @CommandHandler
    public OrderAggregate(CreateOrderCommand command, OrderAggregateUtilService utilService) throws ProductNotFoundException, InsufficientQuantityException {
        log.info("**********************************");
        log.info("CreateOrderCommand received");
        if(utilService.productExists(command.getProductId()))
            throw new ProductNotFoundException("Product not found !!!!");
        if (utilService.quantityAvailable(command.getQuantity(), command.getProductId()))
            throw new InsufficientQuantityException("Insufficient Product Quantity !!!");
        AggregateLifecycle.apply(
                new OrderCreatedEvent(
                        command.getId(),
                        command.getCustomerName(),
                        command.getQuantity(),
                        command.getProductId()
                ));
    }
    @EventSourcingHandler
    public void on(OrderCreatedEvent event){
        log.info("**********************************");
        log.info("OrderCreatedEvent occurred");
        this.id= event.getId();
        this.customerName= event.getCustomerName();
        this.quantity= event.getQuantity();
        this.productId= event.getProductId();
    }
}
