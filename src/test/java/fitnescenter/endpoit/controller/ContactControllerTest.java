package fitnescenter.endpoit.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import fitnescenter.endpoit.entity.CreditCard;
import fitnescenter.endpoit.entity.Users;
import fitnescenter.endpoit.models.CreditCardResponse;
import fitnescenter.endpoit.models.WebResponse;
import fitnescenter.endpoit.repository.CreditCardRepository;
import fitnescenter.endpoit.repository.UserRepository;
import fitnescenter.endpoit.security.BCrypt;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditCardRepository creditcardRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        creditcardRepository.deleteAll();
        userRepository.deleteAll();

        // Users user = new Users();
        // user.setUsername("test");
        // user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
        // user.setName("Test");
        // user.setToken("test");
        // user.setTokenExpiredAt(System.currentTimeMillis() + 1000000);
        // userRepository.save(user);
    }

}
