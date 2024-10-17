package summer.camp.inventory_service.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import summer.camp.inventory_service.entities.Inventory;
import summer.camp.inventory_service.repositories.InventoryRepo;
import summer.camp.queries.GetAllInventoriesQuery;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryQueryHandler {
    private InventoryRepo inventoryRepo;
    @QueryHandler
    public List<Inventory>inventories(GetAllInventoriesQuery query)
    {return inventoryRepo.findAll();}
}
