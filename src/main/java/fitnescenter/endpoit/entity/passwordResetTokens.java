package fitnescenter.endpoit.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = passwordResetTokens.TABLE_NAME)

public class passwordResetTokens {
    public static final String TABLE_NAME = "password_reset_tokens";

    @Email
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("email")
    private String email;

    @JsonProperty("token")
    private String token;

    @JsonProperty("created_at")
    private Date createdAt;
}
