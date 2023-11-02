package com.tttn.qlkho.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tttn.qlkho.Model.Vitrikho;
import com.tttn.qlkho.Response.APIResponse;
import com.tttn.qlkho.Service.VitriKhoService;

@RestController
@RequestMapping("/vitrikho")
// @PreAuthorize("hasRole('ADMIN')")
public class VitriKhoController {
    @Autowired
    VitriKhoService vitrikhoService;

    @GetMapping()
    public APIResponse getAllVitriKho() {
        List<Vitrikho> Vitrikholist = vitrikhoService.getAllVitriKho();
        APIResponse response = new APIResponse(true, Vitrikholist, "danh sach vi tri kho đã được lấy thành công");
        return response;
    }
    // @GetMapping("/kho/vitri")
    // public APIResponse getVitrikhoBySpId(Vitrikho vitrikho) {
    //     String Vitrikholist = vitrikho.getCot();
    //     APIResponse response = new APIResponse(true, Vitrikholist, "danh sach vi tri kho đã được lấy thành công");
    //     return response;
    // }
    @GetMapping("/kho/{khoId}")
    public List<Vitrikho> getVitrikhoBykhoId(@PathVariable long khoId) {
        return vitrikhoService.getVitrikhoByKhoId(khoId);
    }
    @PostMapping("/them")
    public APIResponse createvitrikho(@RequestBody Vitrikho vitrikho) {
        // if(vitrikhoService.getVTCot(vitrikho.getCot()) != null){
        //     APIResponse response = new APIResponse(true, vitrikho, "Cột đã tồn tại");
        //     return response;
        // }
        vitrikhoService.createvtkho(vitrikho);

        APIResponse response = new APIResponse(true, vitrikho, "vi tri kho đã được thêm thành công");

        return response;
    }
    @DeleteMapping("/xoa/{id}")
    public APIResponse deleteVtKho(@PathVariable Long id) {
        try {
            vitrikhoService.deletevtkho(id);
            APIResponse response = new APIResponse(true, null, "vi tri kho đã được xóa thành công");
            return response;
        } catch (Exception e) {
            APIResponse response = new APIResponse(true, null, "vi tri kho đã được sử dụng!");
            return response;
        }
    }
    @PutMapping("/sua/{id}")
    public APIResponse SuaNhaCungCap(@PathVariable long id, @RequestBody Vitrikho vitrokho) {
        try{
        Vitrikho updateVtkho = vitrikhoService.getvtkhoById(id);
            if(updateVtkho==null) {
                APIResponse response = new APIResponse(false, null, "vi tri kho khong co");

                return response;
            }else {
                if (vitrokho.getCot() != null) {
                    updateVtkho.setCot(vitrokho.getCot());
                }
                if (vitrokho.getHang() != null) {
                    updateVtkho.setHang(vitrokho.getHang());
                }

                if (vitrokho.getKe() != null) {
                    updateVtkho.setKe(vitrokho.getKe());
                }

                vitrikhoService.updatevtkhobyid(id, updateVtkho);
                APIResponse response = new APIResponse(true, updateVtkho, "ok");
                return response;
            }
        }
        catch (Exception e) {
            APIResponse response = new APIResponse(false, e, null);
            return response;
        }
}
}
