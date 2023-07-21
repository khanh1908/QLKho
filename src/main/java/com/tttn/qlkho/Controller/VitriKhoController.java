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
@PreAuthorize("hasRole('ADMIN')")
public class VitriKhoController {
    @Autowired
    VitriKhoService vitrikhoService;

    @GetMapping()
    public APIResponse getAllVitriKho() {
        List<Vitrikho> Vitrikholist = vitrikhoService.getAllVitriKho();
        APIResponse response = new APIResponse(true, Vitrikholist, "danh sach vi tri kho đã được lấy thành công");
        return response;
    }
    @PostMapping("/them")
    public APIResponse createvitrikho(@RequestBody Vitrikho vitrikho) {
        // Logic để thêm kho vào cơ sở dữ liệu
        // Ví dụ:
        vitrikhoService.createvtkho(vitrikho);

        // Tạo đối tượng APIResponse
        APIResponse response = new APIResponse(true, vitrikho, "vi tri kho đã được thêm thành công");

        return response;
    }
    @DeleteMapping("/xoa/{id}")
    public APIResponse deleteVtKho(@PathVariable Long id) {
        // Logic để xóa kho từ cơ sở dữ liệu
        // Ví dụ:
        vitrikhoService.deletevtkho(id);

        // Tạo đối tượng APIResponse
        APIResponse response = new APIResponse(true, null, "vi tri kho đã được xóa thành công");

        return response;
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

                if (vitrokho.getSoLuong() != 0) {
                    updateVtkho.setSoLuong(vitrokho.getSoLuong());
                }


                vitrikhoService.updatevtkho(id, updateVtkho);
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
