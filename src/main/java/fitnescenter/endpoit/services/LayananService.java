package fitnescenter.endpoit.services;


import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import fitnescenter.endpoit.entity.DetailLayanan;
import fitnescenter.endpoit.entity.MenuLayanan;
import fitnescenter.endpoit.entity.OrderPaket;
import fitnescenter.endpoit.entity.OrderRequest;
import fitnescenter.endpoit.entity.TambahPertemuanRequest;
import fitnescenter.endpoit.entity.TambahanPertemuan;
import fitnescenter.endpoit.entity.User;
import fitnescenter.endpoit.models.OrderResponse;
import fitnescenter.endpoit.repository.DetailLayananRepository;
import fitnescenter.endpoit.repository.MenuLayananRepository;
import fitnescenter.endpoit.repository.OrderPaketRepository;
import fitnescenter.endpoit.repository.TambahanPertemuanRepository;
// import fitnescenter.endpoit.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
// @Slf4j
public class LayananService {

    private final OrderPaketRepository orderPaketRepository;

    private final MenuLayananRepository menuLayananRepository;

    private final DetailLayananRepository detailLayananRepository;

    private final TambahanPertemuanRepository tambahanPertemuanRepository;

    // private final UserRepository userRepository;

    private final ValidationService validationService;

    @Transactional
    public List<MenuLayanan> getPaket(){
        return menuLayananRepository.findAll();
    }

    @Transactional
    public OrderResponse orderPaket(User user, OrderRequest request){
        validationService.validate(request);

        MenuLayanan menuLayanan = menuLayananRepository.findFirstByNamaPaket(request.getPaketLayanan())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paket layanan tidak ada"));

        if (orderPaketRepository.existsByUser(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anda sudah berlanggganan");
        }

        OrderPaket orderPaket = new OrderPaket();
        orderPaket.setId(UUID.randomUUID().toString());
        orderPaket.setMenuLayanan(menuLayanan);
        orderPaket.setUser(user);
        orderPaketRepository.save(orderPaket);

        return OrderResponse.builder()
                .pemberitahuan("Order Paket Berhasil")
                .idOrder(orderPaket.getId())
                .build();

    }

    @Transactional
    public Optional<OrderPaket> getOrderPaketUser(User user){
        return orderPaketRepository.findByUser(user);
    }


    @Transactional
    public void tambahPertemuan(User user, TambahPertemuanRequest request){
        validationService.validate(request);

        DetailLayanan detailLayanan = detailLayananRepository.findFirstByNamaLatihan(request.getNamaLatihan())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "List Latihan yg dipilih tidak ada"));

        OrderPaket getIdOrderPaket = orderPaketRepository.findFirstById(request.getOrderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id order tidak ada"));

        if (tambahanPertemuanRepository.existsByIdOrderAndTambahanLatihan(getIdOrderPaket.getId(), detailLayanan.getNamaLatihan())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anda sudah menambahkan jenis latihan ini");
        }

        TambahanPertemuan tambahanPertemuan = new TambahanPertemuan();
        tambahanPertemuan.setIdOrder(getIdOrderPaket.getId());
        tambahanPertemuan.setTambahanLatihan(detailLayanan.getNamaLatihan());
        tambahanPertemuanRepository.save(tambahanPertemuan);

    }

    @Transactional
    public void hapusOrderTambahanPertemuan(User user, String idOrder, Integer idTambahanPertemuan){

        TambahanPertemuan tambahanPertemuan = tambahanPertemuanRepository.findFirstByIdOrderAndId(idOrder, idTambahanPertemuan)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id order atau id tambahan tidak ada"));

        tambahanPertemuanRepository.delete(tambahanPertemuan);
    }

    @Transactional
    public void hapusOrderPaket(User user, String idOrder){

        OrderPaket orderPaket = orderPaketRepository.findFirstById(idOrder)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id order tidak ada"));

        orderPaketRepository.delete(orderPaket);
    }


}
