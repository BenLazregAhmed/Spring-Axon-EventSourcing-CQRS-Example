package summer.camp.order_service.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import summer.camp.commands.CreateOrderCommand;
import summer.camp.dtos.CreateOrderRequestDto;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/orders/commands")
@AllArgsConstructor
public class OrderCommandRestController {
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createOrder(CreateOrderRequestDto requestDto)
    {
        return commandGateway.send(
                new CreateOrderCommand(
                        UUID.randomUUID().toString(),
                        requestDto.getQuantity(),
                        requestDto.getCustomerName(),
                        requestDto.getProductId()
                )
        );
    }
}
