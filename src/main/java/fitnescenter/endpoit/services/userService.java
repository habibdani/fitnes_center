package fitnescenter.endpoit.services;

import javax.xml.transform.Source;
import javax.xml.validation.Validator;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fitnescenter.endpoit.entity.Users;
import fitnescenter.endpoit.models.RegisterUserRequest;
import fitnescenter.endpoit.repository.UserRepository;
import fitnescenter.endpoit.security.BCrypt;
@Service
public class userService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public void register(RegisterUserRequest request){
        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);
        if (constraintViolations.size() != 0) {
            throw new ConstraintViolationException(constraintViolations);        
        }

        if (UserRepository.existsById(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already registered.");
        }

        Users users = new Users();
        users.setUsername(request.getUsername());
        users.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        users.setEmail(request.getEmail());
        users.setPhoneNumber(request.getPhoneNumber());

        UserRepository.save(users);
    }
    
}
