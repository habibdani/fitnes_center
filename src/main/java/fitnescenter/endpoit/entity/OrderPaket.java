package fitnescenter.endpoit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_paket")
public class OrderPaket {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_order", referencedColumnName = "name")
    private User user;

    @ManyToOne
    @JoinColumn(name = "nama_paket_order", referencedColumnName = "nama_paket")
    private MenuLayanan menuLayanan;

    @ManyToMany
    @JoinTable(name = "tambahan_pertemuan",
            joinColumns = {
                    @JoinColumn(name = "id_order", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tambahan_latihan", referencedColumnName = "nama_latihan")
            }
    )
    public Set<DetailLayanan> tambahanLayanan;


}

