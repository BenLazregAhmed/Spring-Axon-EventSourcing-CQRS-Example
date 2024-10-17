package summer.camp.product_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter@Builder
@Entity
public class Product {
    @Id
    private String  id;
    private String name;
    private double price;
    private Date expirationDate;
    private int quantity;
    @ManyToOne
    private Inventory inventory;
}
