package fitnescenter.endpoit.models;

// import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserResponse {

    private String name;

    private String email;

    private String phone;

    private String nameCreditCard;

    private String numberCreditCard;

    private Date expiredCreditCard;
}
