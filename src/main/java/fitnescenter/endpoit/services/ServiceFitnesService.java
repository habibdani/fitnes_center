package fitnescenter.endpoit.services;

import fitnescenter.endpoit.models.MenuResponse;
import fitnescenter.endpoit.entity.ServiceFitnes;
import org.springframework.stereotype.Service;

@Service
public class ServiceFitnesService {

    public MenuResponse get_menu(ServiceFitnes layanan){
        return MenuResponse.builder()
                .menu_name(layanan.getMenu_name())
                .duration(layanan.getDuration())
                .build();
    }
}
