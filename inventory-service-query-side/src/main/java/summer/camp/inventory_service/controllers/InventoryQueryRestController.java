package summer.camp.inventory_service.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import summer.camp.inventory_service.entities.Inventory;
import summer.camp.queries.GetAllInventoriesQuery;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/inventory/query")
public class InventoryQueryRestController {
    private QueryGateway queryGateway;

    @GetMapping("/all")
    public CompletableFuture<List<Inventory>>inventories()
    {
        return queryGateway.query(
                new GetAllInventoriesQuery(),
                ResponseTypes.multipleInstancesOf(Inventory.class)
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String > responseEntity=
                new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}
