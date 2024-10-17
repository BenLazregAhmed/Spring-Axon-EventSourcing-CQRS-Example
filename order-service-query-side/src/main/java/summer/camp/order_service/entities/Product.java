package summer.camp.order_service.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private String inventoryId;
}
