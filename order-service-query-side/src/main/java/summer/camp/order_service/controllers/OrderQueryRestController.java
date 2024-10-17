package summer.camp.order_service.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import summer.camp.order_service.entities.Order;
import summer.camp.queries.GetAllOrdersQuery;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/orders/query")
@AllArgsConstructor
public class OrderQueryRestController {
    private QueryGateway queryGateway;
    @GetMapping("/all")
    public CompletableFuture<List<Order>> getOrders()
    {
        return queryGateway.query(new GetAllOrdersQuery(), ResponseTypes.multipleInstancesOf(Order.class));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String > responseEntity=
                new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}
