package fitnescenter.endpoit.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.print.DocFlavor.STRING;

// import org.hibernate.mapping.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Users.TABLE_NAME)

public class Users {
 public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("UUID")
    private String UUID;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;
    
    @JsonProperty("password")
    private String password;

    @JsonProperty("phone_number")
    private Integer phoneNumber;

    @JsonProperty("status_active")
    private Integer statusActive;

    @JsonProperty("created_at")
    private Date createdAt;

     @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @OneToOne(mappedBy = "users")
    private List<CreditCard> credit_card;

    @OneToOne(mappedBy = "users")
    private List<oauthAccessTokens> oauth_access_tokens;

    @OneToOne(mappedBy = "users")
    private List<passwordResetTokens> password_reset_tokens;
}