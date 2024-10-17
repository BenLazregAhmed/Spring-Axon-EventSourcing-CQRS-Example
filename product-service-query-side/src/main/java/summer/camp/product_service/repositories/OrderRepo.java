package summer.camp.product_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import summer.camp.product_service.entities.Order;

public interface OrderRepo extends JpaRepository<Order,String > {
}
