package summer.camp.order_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import summer.camp.order_service.entities.Order;

public interface OrderRepo extends JpaRepository<Order ,String> {
}
