package fitnescenter.endpoit.services;

import fitnescenter.endpoit.models.ForgotPasswordRequest;
import fitnescenter.endpoit.models.RegisterUserRequest;
import fitnescenter.endpoit.models.SetPasswordRequest;
import fitnescenter.endpoit.models.UpdateUserRequest;
import fitnescenter.endpoit.models.UpdateUserResponse;
import fitnescenter.endpoit.models.UserResponse;
import fitnescenter.endpoit.entity.User;
import fitnescenter.endpoit.repository.UserRepository;
import fitnescenter.endpoit.security.BCrypt;
import fitnescenter.endpoit.utility.Emailu;
import fitnescenter.endpoit.utility.Otpu;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class UserService {

    @Autowired
    private Otpu otpUtil;

    @Autowired
    private Emailu emailUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void register(RegisterUserRequest request){
        validationService.validate(request);

        if (userRepository.existsUserByEmail(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
        }

        String otp = otpUtil.generatorOtp();
        try{
            emailUtil.sendOtpEmail(request.getEmail(), otp);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to send otp please try again");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setPhone(request.getPhone());
        user.setOtp(otp);
        user.setOtpTime(LocalDateTime.now());
        user.setNameCreditCard(request.getNameCreditCard());
        user.setNumberCreditCard(request.getNumberCreditCard());
        user.setExpiredCreditCard(request.getExpiredCreditCard());

        userRepository.save(user);
    }

    @Transactional
    public String verifyAccount (String email, String otp){
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found"));

         if (user.getOtp().equals(otp) && Duration.between(user.getOtpTime(),LocalDateTime.now()).getSeconds() < (1 * 60)){
            user.setActive(true);
            userRepository.save(user);
            return "OTP verified yo can login";
        }else if (!user.getOtp().equals(otp)){
            return "wrong otp";
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please regenerate otp because otp expired and try again");
    }

    @Transactional
    public String regenerateOtp(String email){
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with this email"));

        String otp = otpUtil.generatorOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        }catch (MessagingException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to send otp please try again");
        }

        user.setOtp(otp);
        user.setOtpTime(LocalDateTime.now());
        userRepository.save(user);

        return "Otp sent to email " + email + ". Please verify account within 1 minute";
    }

    @Transactional
    public String getStatusAccount(String email){
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with this email"));

        if (user.isActive()){
            return "Verified Account";
        } else {
            return "The account has not been verified";
        }
    }
    public UserResponse get(User user){
        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .nameCreditCard(user.getNameCreditCard())
                .numberCreditCard(user.getNumberCreditCard())
                .expiredCreditCard(user.getExpiredCreditCard())
                .build();
    }

    @Transactional
    public UpdateUserResponse update(User user, UpdateUserRequest request) {
        validationService.validate(request);

        log.info("REQUEST : {}", request);

        if(Objects.nonNull(user.getName())){
            user.setName(request.getName());
        }

        if (Objects.nonNull(user.getEmail())){
            user.setEmail(request.getEmail());
        }

        if (Objects.nonNull(user.getPhone())){
            user.setPhone(request.getPhone());
        }

        if (Objects.nonNull(user.getPassword())){
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        if (Objects.nonNull(user.getNameCreditCard())) {
            user.setNameCreditCard(request.getNameCreditCard());
        }

        if (Objects.nonNull(user.getNumberCreditCard())){
            user.setNumberCreditCard(request.getNumberCreditCard());
        }

        if (Objects.nonNull(user.getExpiredCreditCard())){
            user.setExpiredCreditCard(request.getExpiredCreditCard());
        }

        return UpdateUserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .nameCreditCard(user.getNameCreditCard())
                .numberCreditCard(user.getNumberCreditCard())
                .expiredCreditCard(user.getExpiredCreditCard())
                .build();
    }

    @Transactional
    public void forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findFirstByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with this email"));

        String otp = otpUtil.generatorOtp();
        try{
            emailUtil.setOtpForgotPassword(request.getEmail(), otp);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to send otp please try again");
        }

        user.setOtp(otp);
        user.setOtpTime(LocalDateTime.now());
        userRepository.save(user);
    }

    @Transactional
    public String setPassword(SetPasswordRequest request) {
        User user = userRepository.findFirstByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found"));

        if (user.getOtp().equals(request.getOtp()) && Duration.between(user.getOtpTime(),LocalDateTime.now()).getSeconds() < (1 * 60)){
            user.setPassword(BCrypt.hashpw(request.getNewPassword(), BCrypt.gensalt()));
            userRepository.save(user);
            return "Set new password success, please login again";
        }else if (!user.getOtp().equals(request.getOtp())){
            return "wrong otp";
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please regenerate otp because otp expired and try again");
    }
}
