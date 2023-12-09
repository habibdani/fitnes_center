package fitnescenter.endpoit.entity;

import java.sql.Date;
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
@Table(name = oauthClients.TABLE_NAME)

public class oauthClients {
    
    public static final String TABLE_NAME = "oauth_clients";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonProperty("user_id")
    private Users users;

    @JsonProperty("name")
    private String name;

    @JsonProperty("secret")
    private String secret;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("redirect")
    private Character redirect;

    @JsonProperty("personal_access_client")
    private Integer personalAccessClient;

    @JsonProperty("password_client")
    private Integer passwordClient;

    @JsonProperty("revoked")
    private Integer revoked;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @OneToOne(mappedBy = "oauth_clients")
    private List<oauthAccessTokens> oauth_access_tokens;
}
