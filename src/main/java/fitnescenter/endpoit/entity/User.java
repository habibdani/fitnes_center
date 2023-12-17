package fitnescenter.endpoit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "fk_name_user", columnList = "name")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String password;

    private String phone;

    private boolean active;

    private String otp;

    @Column(name = "otp_time")
    private LocalDateTime otpTime;

    private String token;

    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;

    @Column(name = "name_credit_card")
    private String nameCreditCard;

    @Column(name = "number_credit_card")
    private String numberCreditCard;

    @Column(name = "expired_credit_card")
    private Date expiredCreditCard;

}
