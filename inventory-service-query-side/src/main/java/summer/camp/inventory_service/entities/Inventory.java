package summer.camp.inventory_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    private List<Product>products=new ArrayList<>();
}
