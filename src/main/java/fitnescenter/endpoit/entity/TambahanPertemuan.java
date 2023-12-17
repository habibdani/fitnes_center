package fitnescenter.endpoit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tambahan_pertemuan")
public class TambahanPertemuan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_order")
    private String idOrder;

    @Column(name = "tambahan_latihan")
    private String tambahanLatihan;
}
