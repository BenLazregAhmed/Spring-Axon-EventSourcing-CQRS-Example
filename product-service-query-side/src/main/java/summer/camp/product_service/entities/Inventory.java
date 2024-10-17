package summer.camp.product_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter@Builder
@Entity
public class Inventory {
    @Id
    private String id;
    private Date creationDate;
    @OneToMany(mappedBy = "inventory")
    @JsonIgnore
    private List<Product>products;
}
