package fitnescenter.endpoit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu_service")
public class ServiceFitnes {

    @Id
    private String menu_name;

    private Integer duration;


}
