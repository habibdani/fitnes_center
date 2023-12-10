package fitnescenter.endpoit.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import fitnescenter.endpoit.entity.Users;
import fitnescenter.endpoit.models.RegisterUserRequest;
import fitnescenter.endpoit.models.UpdateUserRequest;
import fitnescenter.endpoit.models.UserResponse;
import fitnescenter.endpoit.repository.UserRepository;
import fitnescenter.endpoit.security.BCrypt;

import java.util.Objects;

@Service
public class userService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void register(RegisterUserRequest request) {
        validationService.validate(request);

        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        Users users = new Users();
        users.setUsername(request.getUsername());
        users.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        users.setEmail(request.getEmail());

        userRepository.save(users);
    }

    public UserResponse get(Users users) {
        return UserResponse.builder()
                .username(users.getUsername())
                .email(users.getEmail())
                .build();
    }

}
