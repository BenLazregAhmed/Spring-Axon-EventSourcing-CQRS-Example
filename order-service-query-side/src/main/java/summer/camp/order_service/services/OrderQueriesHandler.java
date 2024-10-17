package summer.camp.order_service.services;

import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import summer.camp.order_service.entities.Order;
import summer.camp.order_service.repositories.OrderRepo;
import summer.camp.queries.GetAllOrdersQuery;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderQueriesHandler {
    private OrderRepo orderRepo;
    @QueryHandler
    public List<Order>orderList(GetAllOrdersQuery getAllOrdersQuery){return orderRepo.findAll();}
}
