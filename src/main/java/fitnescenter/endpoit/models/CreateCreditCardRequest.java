package fitnescenter.endpoit.models;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCreditCardRequest {

    @NotBlank
    @Size(max = 100)
    private String nomorKartu;

    @NotBlank
    @Size(max = 100)
    private String cvv;

    @NotBlank
    @Size(max = 100)
    private Date expiredDate;

}
   
