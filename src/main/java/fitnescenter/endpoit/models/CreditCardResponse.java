package fitnescenter.endpoit.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardResponse {

    private Long id;

    private String nomorKartu;

    private String cvv;
    
    private String userId;
   
    private Date expiredDate;

    private Date createdAt;
}
