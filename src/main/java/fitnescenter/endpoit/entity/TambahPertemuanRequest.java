package fitnescenter.endpoit.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TambahPertemuanRequest {

    @NotNull
    private String orderId;

    @NotNull
    private String namaLatihan;
}