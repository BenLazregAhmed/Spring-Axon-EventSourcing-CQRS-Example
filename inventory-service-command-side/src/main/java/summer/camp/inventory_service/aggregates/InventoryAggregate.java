package summer.camp.inventory_service.aggregates;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import summer.camp.commands.CreateInventoryCommand;
import summer.camp.events.InventoryCreatedEvent;

import java.util.Date;

@Aggregate
@NoArgsConstructor
@Slf4j
public class InventoryAggregate {
    @AggregateIdentifier
    private String id;
    private Date creationDate;

    @CommandHandler
    public InventoryAggregate(CreateInventoryCommand command)
    {
        log.info("***************************************");
        log.info("CreateInventoryCommand received");
        AggregateLifecycle.apply(
                new InventoryCreatedEvent(
                        command.getId(),
                        command.getCreationDate()
                        )
        );
    }

    @EventSourcingHandler
    public void on(InventoryCreatedEvent event)
    {
        log.info("***************************************");
        log.info("CreateInventoryCommand occurred");
        this.id= event.getId();
        this.creationDate=event.getCreationDate();
    }

}
