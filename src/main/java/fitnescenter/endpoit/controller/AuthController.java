package fitnescenter.endpoit.controller;

import fitnescenter.endpoit.models.LoginUserRequest;
import fitnescenter.endpoit.models.LoginResponse;
import fitnescenter.endpoit.models.WebResponseSuccess;
import fitnescenter.endpoit.entity.User;
import fitnescenter.endpoit.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/api/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseSuccess<LoginResponse> login(@RequestBody LoginUserRequest request){
        LoginResponse loginResponse = authService.login(request);
        return WebResponseSuccess.<LoginResponse>builder()
                .data(loginResponse)
                .build();
    }

    @DeleteMapping(
            path = "/api/auth/logout",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseSuccess<String> logout(User user){
        authService.logout(user);
        return WebResponseSuccess.<String>builder().data("Logout Success").build();
    }
}
