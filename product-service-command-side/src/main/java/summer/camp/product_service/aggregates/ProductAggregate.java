package summer.camp.product_service.aggregates;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import summer.camp.commands.CreateProductCommand;
import summer.camp.commands.DeleteProductCommand;
import summer.camp.commands.UpdateProductCommand;
import summer.camp.events.ProductCreatedEvent;
import summer.camp.events.ProductDeletedEvent;
import summer.camp.events.ProductUpdatedEvent;
import summer.camp.exceptions.InventoryNotFoundException;
import summer.camp.exceptions.NegativeQuantityException;
import summer.camp.exceptions.ProductNotFoundException;
import summer.camp.product_service.services.AggregateUtilService;

import java.util.Date;

@Aggregate
@NoArgsConstructor
@Slf4j
public class ProductAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private double price;
    private Date expirationDate;
    private int quantity;
    private String inventoryId;

    @CommandHandler
    public ProductAggregate(CreateProductCommand command, AggregateUtilService aggregateUtilService) throws InventoryNotFoundException {
        log.info("***************************************");
        log.info("CreateProductCommand received");
        if (!aggregateUtilService.InventoryExists(command.getInventoryId()))
            throw new InventoryNotFoundException("Inventory Not Found !!!");
        AggregateLifecycle.apply(
                new ProductCreatedEvent(
                        command.getId(),
                        command.getName(),
                        command.getPrice(),
                        command.getExpirationDate(),
                        command.getQuantity(),
                        command.getInventoryId()
                )
        );
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event)
    {
        log.info("***************************************");
        log.info("ProductCreatedEvent occurred");
        this.id=event.getId();
        this.name=event.getName();
        this.price= event.getPrice();
        this.expirationDate=event.getExpirationDate();
        this.quantity=event.getQuantity();
        this.inventoryId= event.getInventoryId();
    }

    @CommandHandler
    public void handle(DeleteProductCommand command,AggregateUtilService utilService) throws ProductNotFoundException {
        log.info("***************************************");
        log.info("DeleteProductCommand received");
        if (!utilService.productExists(command.getId()))
            throw new ProductNotFoundException("This Product "+command.getId()+" does not exists !!!");
        AggregateLifecycle.apply(
                new ProductDeletedEvent(
                        command.getId()
                )
        );
    }

    @EventSourcingHandler
    public void on(ProductDeletedEvent event){
        log.info("***************************************");
        log.info("ProductDeletedEvent occurred");
        this.id= event.getId();
        AggregateLifecycle.markDeleted();
    }
    @CommandHandler
    public void handle(UpdateProductCommand command,AggregateUtilService aggregateUtilService) throws InventoryNotFoundException, ProductNotFoundException, NegativeQuantityException {
        log.info("***************************************");
        log.info("UpdateProductCommand received");
        if (!aggregateUtilService.productExists(command.getId()))
            throw new ProductNotFoundException("This Product "+command.getId()+" does not exists !!!");
        if (!aggregateUtilService.InventoryExists(command.getInventoryId()))
            throw new InventoryNotFoundException("Inventory Not Found !!!");
        if (command.getQuantity()<0)
            throw new NegativeQuantityException("The Quantity should not be negative !!!");
        AggregateLifecycle.apply(
                new ProductUpdatedEvent(
                        command.getId(),
                        command.getName(),
                        command.getPrice(),
                        command.getExpirationDate(),
                        command.getQuantity(),
                        command.getInventoryId()
                )
        );
    }
    @EventSourcingHandler
    public void on(ProductUpdatedEvent event)
    {
        log.info("***************************************");
        log.info("ProductUpdatedEvent occurred");
        this.id=event.getId();
        this.name=event.getName();
        this.price= event.getPrice();
        this.expirationDate=event.getExpirationDate();
        this.quantity=event.getQuantity();
        this.inventoryId= event.getInventoryId();
    }

}
