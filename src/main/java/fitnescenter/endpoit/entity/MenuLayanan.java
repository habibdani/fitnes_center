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
@Table(
        name = "menu_layanan"
)
public class MenuLayanan {

    @Id
    @Column(name = "nama_paket")
    private String namaPaket;

    @Column(name = "jumlah_pertemuan")
    private String jumlahPertemuan;

    @ManyToMany
    @JoinTable(name = "paket_layanan",
            joinColumns = {
                @JoinColumn(name = "nama_paket", referencedColumnName = "nama_paket")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "nama_latihan", referencedColumnName = "nama_latihan")
            }
    )
    public Set<DetailLayanan> detailLayanans;

}
