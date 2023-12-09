package fitnescenter.endpoit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

@JsonProperty("access_token_id")
private String accesstokenid;
revoked
expires_at


}
