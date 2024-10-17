package summer.camp.order_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter@Builder
@Entity
@Table(name = "myOrder")
public class Order {
    @Id
    private String id;
    private int quantity;
    private String customerName;
    @ManyToOne
    private Product product;
}
