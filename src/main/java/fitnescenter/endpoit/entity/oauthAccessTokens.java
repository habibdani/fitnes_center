package fitnescenter.endpoit.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

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
@Table(name = oauthAccessTokens.TABLE_NAME)

public class oauthAccessTokens {
    
    public static final String TABLE_NAME = "oauth_access_tokens";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonProperty("user_id")
    private Users users;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonProperty("client_id")
    private oauthClients oauth_clients;

    @JsonProperty("name")
    private String name;

    @JsonProperty("scopes")
    private String scopes;

    @JsonProperty("revoked")
    private String revoked;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @OneToOne(mappedBy = "oauth_access_tokens")
    private List<oauthRefreshTokens> oauth_refresh_tokens;

}
