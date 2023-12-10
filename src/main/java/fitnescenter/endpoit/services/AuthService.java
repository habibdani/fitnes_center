package fitnescenter.endpoit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import fitnescenter.endpoit.entity.Users;
import fitnescenter.endpoit.entity.oauthAccessTokens;
import fitnescenter.endpoit.models.LoginUserRequest;
import fitnescenter.endpoit.models.TokenResponse;
import fitnescenter.endpoit.repository.UserRepository;
import fitnescenter.endpoit.security.BCrypt;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private oauthAccessTokens oauthAccessTokens;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);

        oauthAccessTokens oauthAccessTokens = oauthAccessTokens.findByuserId(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong"));

        // if (BCrypt.checkpw(request.getPassword(), users.getPassword())) {
    //         users.setToken(UUID.randomUUID().toString());
    //         users.setTokenExpiredAt(next30Days());
    //         userRepository.save(users);

    //         return TokenResponse.builder()
    //                 .token(users.getToken())
    //                 .expiredAt(users.getTokenExpiredAt())
    //                 .build();
    //     } else {
    //         throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
    //     }
    // }

    // private Long next30Days() {
    //     return System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 30);
    // }

    // @Transactional
    // public void logout(Users users) {
    //     users.setToken(null);
    //     users.setTokenExpiredAt(null);

    //     userRepository.save(users);
    // }
}
