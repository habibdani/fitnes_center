package fitnescenter.endpoit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detail_layanan")
public class DetailLayanan {

    @Id
    @Column(name = "nama_latihan")
    private String namaLatihan;

    private Long harga;

    private String hari;

    @Column(name = "durasi_menit")
    private String durasiMenit;


}
