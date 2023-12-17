package fitnescenter.endpoit.services;

import fitnescenter.endpoit.entity.User;
import fitnescenter.endpoit.models.LoginResponse;
import fitnescenter.endpoit.models.LoginUserRequest;
import fitnescenter.endpoit.repository.UserRepository;
import fitnescenter.endpoit.security.BCrypt;
// import fitnescenter.endpoit.utility.Emailu;
// import fitnescenter.endpoit.utility.Otpu;
// import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
// @Slf4j
public class AuthService {

    // @Autowired
    // private Otpu otpUtil;

    // @Autowired
    // private  Emailu emailUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public LoginResponse login(LoginUserRequest request){
        validationService.validate(request);

        User user = userRepository.findFirstByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email atau Password salah ciiinnnn ... !!!"));

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email atau Password salah ciiinnnn ... !!!");
        }else if (!user.isActive()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Your account is not verified");
        }else {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(tokenExpired());
            userRepository.save(user);

            return LoginResponse.builder()
                    .name(user.getName())
                    .token(user.getToken())
                    .tokenExpiredAt(user.getTokenExpiredAt())
                    .build();
        }
    }

    @Transactional
    public void logout(User user){
        user.setToken(null);
        user.setTokenExpiredAt(null);
        userRepository.save(user);
    }

    private Long tokenExpired() {
        return System.currentTimeMillis() + (6000000);
    }

}
