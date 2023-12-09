package fitnescenter.endpoit.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = oauthRefreshTokens.TABLE_NAME)

public class oauthRefreshTokens {
    public static final String TABLE_NAME = "oauth_refresh_tokens";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne
    @JoinColumn(name = "access_token_id", referencedColumnName = "id")
    @JsonProperty("access_token_id")
    private oauthAccessTokens oauth_access_tokens;

    @JsonProperty("revoked")
    private Integer revoked;

    @JsonProperty("expires_at")
    private Date expiresAt;

}
