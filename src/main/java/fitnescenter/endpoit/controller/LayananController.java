package fitnescenter.endpoit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import fitnescenter.endpoit.entity.MenuLayanan;
import fitnescenter.endpoit.entity.OrderPaket;
import fitnescenter.endpoit.entity.OrderRequest;
import fitnescenter.endpoit.entity.TambahPertemuanRequest;
import fitnescenter.endpoit.entity.User;
import fitnescenter.endpoit.models.OrderResponse;
import fitnescenter.endpoit.models.WebResponseSuccess;
// import fitnescenter.endpoit.repository.MenuLayananRepository;
// import fitnescenter.endpoit.repository.OrderPaketRepository;
import fitnescenter.endpoit.services.LayananService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(
        path = "/api/paket"
)
public class LayananController {

    // private final OrderPaketRepository orderPaketRepository;

    // private final MenuLayananRepository menuLayananRepository;

    private final LayananService layananService;

    @GetMapping
    public List<MenuLayanan> getPaket(){
        return layananService.getPaket();
    }

    @GetMapping(
            path = "/get-order-paket"
    )
    public Optional<OrderPaket> getPaketOrder(User user){
        return layananService.getOrderPaketUser(user);
    }


    @PostMapping(
            path = "/order-paket"
    )
    public WebResponseSuccess<OrderResponse> orderPaket(User user, @RequestBody OrderRequest request){
        OrderResponse orderResponse = layananService.orderPaket(user, request);
        return WebResponseSuccess.<OrderResponse>builder().data(orderResponse).build();
    }

    @PostMapping(
            path = "/tambah-pertemuan"
    )
    public WebResponseSuccess<String> tambahPertemuan(User user, @RequestBody TambahPertemuanRequest request){
        layananService.tambahPertemuan(user, request);
        return WebResponseSuccess.<String>builder().data("Berhasil tambah pertemuan").build();
    }

    @DeleteMapping(
            path = "/hapus-order-tambahan/{idOrder}/{idTambahanPertemuan}"
    )
    public WebResponseSuccess<String> hapusOrder(
                                                    User user,
                                                    @PathVariable(name = "idOrder") String idOrder,
                                                    @PathVariable(name = "idTambahanPertemuan") Integer idTambahanPertemuan){
        layananService.hapusOrderTambahanPertemuan(user, idOrder, idTambahanPertemuan);
        return WebResponseSuccess.<String>builder().data("Menghapus order tambahan pertemuan sukses").build();
    }

    @DeleteMapping(
            path = "/hapus-order-paket/{idOrder}"
    )
    public WebResponseSuccess<String> hapusOrderPaket(User user, @PathVariable(name = "idOrder") String idOrder){
        layananService.hapusOrderPaket(user, idOrder);
        return WebResponseSuccess.<String>builder().data("Menghapus paket order sukses").build();
    }


}
