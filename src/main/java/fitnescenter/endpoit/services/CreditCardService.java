package fitnescenter.endpoit.services;

import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import fitnescenter.endpoit.entity.CreditCard;
import fitnescenter.endpoit.entity.Users;
import fitnescenter.endpoit.models.CreditCardResponse;
import fitnescenter.endpoit.models.CreateCreditCardRequest;
import fitnescenter.endpoit.repository.CreditCardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditcardRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public CreditCardResponse create(Users user, CreateCreditCardRequest request) {
        validationService.validate(request);

        CreditCard creditcard = new CreditCard();
        creditcard.setNomorKartu(request.getNomorKartu());
        creditcard.setCvv(request.getCvv());
        creditcard.setExpiredDate(request.getExpiredDate());

        creditcardRepository.save(creditcard);

        return toCreditCrdResponse(creditcard);
    }

    private CreditCardResponse toCreditCrdResponse(CreditCard creditcard) {
        return CreditCardResponse.builder()
                .id(creditcard.getId())
                .nomorKartu(creditcard.getNomorKartu())
                .cvv(creditcard.getCvv())
                .expiredDate(creditcard.getExpiredDate())
                .build();
    }

    @Transactional(readOnly = true)
    public CreditCardResponse get(Users users, String id) {
        CreditCard creditcard = creditcardRepository.findFirstByUserId(users)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CreditCrad not found"));

        return toCreditCrdResponse(creditcard);
    }

}
