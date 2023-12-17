package fitnescenter.endpoit.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String name;

    private String email;

    private String phone;

    private String nameCreditCard;

    private String numberCreditCard;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expiredCreditCard;

}
