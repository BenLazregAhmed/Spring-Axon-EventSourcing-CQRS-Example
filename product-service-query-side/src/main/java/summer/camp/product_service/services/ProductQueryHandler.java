package summer.camp.product_service.services;

import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import summer.camp.product_service.entities.Product;
import summer.camp.product_service.repositories.ProductRepo;
import summer.camp.queries.GetAllProductsQuery;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductQueryHandler {

    private ProductRepo productRepo;

    @QueryHandler
    public List<Product>products(GetAllProductsQuery getAllProductsQuery){return productRepo.findAll();}

}
