package fitnescenter.endpoit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailPaketResponse {

    private String namaLatihan;

    private Long harga;

    private String hari;

    private LocalDateTime durasiMenit;

}
