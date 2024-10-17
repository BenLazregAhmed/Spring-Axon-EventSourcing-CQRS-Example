package summer.camp.product_service.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import summer.camp.commands.CreateProductCommand;
import summer.camp.commands.DeleteProductCommand;
import summer.camp.commands.UpdateProductCommand;
import summer.camp.dtos.CreateProductRequestDto;
import summer.camp.dtos.UpdateProductRequestDto;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/products/commands")
public class ProductCommandRestController {
    private CommandGateway commandGateway;

    @PostMapping("/create")
    public CompletableFuture<String> createProduct(@RequestBody CreateProductRequestDto createProductRequestDto)
    {
        return commandGateway.send(
                new CreateProductCommand(
                        UUID.randomUUID().toString(),
                        createProductRequestDto.getName(),
                        createProductRequestDto.getPrice(),
                        createProductRequestDto.getExpirationDate(),
                        createProductRequestDto.getQuantity(),
                        createProductRequestDto.getInventoryId()
                )
        );
    }
    @DeleteMapping(path = "/delete/{productId}")
    public CompletableFuture<String> deleteProduct(@PathVariable String productId){
        return commandGateway.send(
          new DeleteProductCommand(
                  productId
          )
        );
    }
    @PutMapping(path = "/update")
    public CompletableFuture<String> updateProduct(@RequestBody UpdateProductRequestDto updateProductRequestDto)
    {
        return commandGateway.send(
                new UpdateProductCommand(
                        updateProductRequestDto.getId(),
                        updateProductRequestDto.getName(),
                        updateProductRequestDto.getPrice(),
                        updateProductRequestDto.getExpirationDate(),
                        updateProductRequestDto.getQuantity(),
                        updateProductRequestDto.getInventoryId()
                )
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String > responseEntity=
                new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}
