package fitnescenter.endpoit.controller;

import fitnescenter.endpoit.models.MenuResponse;
import fitnescenter.endpoit.models.WebResponseSuccess;
import fitnescenter.endpoit.entity.ServiceFitnes;
import fitnescenter.endpoit.repository.ServiceFitnesRepository;
import fitnescenter.endpoit.services.ServiceFitnesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceFitnesController {

    @Autowired
    private ServiceFitnesRepository servicefitnesRepository;

    @Autowired
    private ServiceFitnesService serviceFitnesService;

    @GetMapping(
            path = "/api/menu"
    )
    public WebResponseSuccess<MenuResponse> get_menu(ServiceFitnes serviceFitnes){
        MenuResponse response = serviceFitnesService.get_menu(serviceFitnes);
        return WebResponseSuccess.<MenuResponse>builder().data(response).build();
    }

}
