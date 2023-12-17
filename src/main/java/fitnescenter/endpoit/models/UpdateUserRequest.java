package fitnescenter.endpoit.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 100)
    private String phone;

    @NotBlank
    @Size(max = 100)
    @Column(name = "name_credit_card")
    private String nameCreditCard;

    @NotBlank
    @Size(max = 100)
    @Column(name = "number_credit_card")
    private String numberCreditCard;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expired_credit_card")
    private Date expiredCreditCard;

}
