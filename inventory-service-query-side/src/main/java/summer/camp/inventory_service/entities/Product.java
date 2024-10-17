package summer.camp.inventory_service.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter@Builder
@Entity
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private Date expirationDate;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
}
