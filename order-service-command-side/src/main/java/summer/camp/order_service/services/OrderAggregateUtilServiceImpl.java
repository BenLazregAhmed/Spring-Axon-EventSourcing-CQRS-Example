package summer.camp.order_service.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import summer.camp.order_service.entities.Product;
import summer.camp.order_service.repositories.ProductRepo;

@Service
@AllArgsConstructor
public class OrderAggregateUtilServiceImpl implements OrderAggregateUtilService {
    private ProductRepo productRepo;
    @Override
    public boolean productExists(String productId) {
        Product product=productRepo.findById(productId).orElse(null);
        return product!=null;
    }

    @Override
    public boolean quantityAvailable(int quantity, String productId) {
        Product product=productRepo.findById(productId).orElse(null);
        if (product==null)
            return false;
        return product.getQuantity()>=quantity;
    }
}
