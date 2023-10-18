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

import com.tttn.qlkho.Model.Kho;
import com.tttn.qlkho.Model.SanPham;
import com.tttn.qlkho.Response.APIResponse;
import com.tttn.qlkho.Service.KhoService;

@RestController
@RequestMapping("/kho")
// @PreAuthorize("hasRole('ADMIN')")
public class KhoController {
    @Autowired
    KhoService khoService;

    @GetMapping()
    public APIResponse getAllKho() {
        List<Kho> khoList = khoService.getAllKho();
        APIResponse response = new APIResponse(true, khoList, "Danh sách kho đã được lấy thành công");
        return response;
    }
    @GetMapping("/{id}")
    public APIResponse getKhoById(@PathVariable long id){

        try {
            Kho kho = khoService.getKhoById(id);
            return new APIResponse(true, kho, "Tìm kiếm kho thanh cong: "+id);
        } catch (Exception e) {
            return new APIResponse(false, null, "tim kiem khong thanh cong");
        }
    }
    @PostMapping("/them")
    public APIResponse createKho(@RequestBody Kho kho) {
        if (khoService.getKhoByName(kho.getTenKho()) != null){
            APIResponse response = new APIResponse(false, null, "Tên kho đã tồn tại");
            return response;
        }
        if (khoService.getKhoByEmail(kho.getEmail()) != null){
            APIResponse response = new APIResponse(false, null, "Tên email đã tồn tại");
            return response;
        }
        if (khoService.getKhoBySDT(kho.getSDT()) != null){
            APIResponse response = new APIResponse(false, null, "Số điện thoại đã tồn tại");
            return response;
        }
        if (kho.getTenKho() == null || kho.getTenKho().isEmpty()) {
            // Trường tên kho không được trống
            APIResponse response = new APIResponse(false, null, "Tên kho không được trống");
            return response;
        }
    
        if (kho.getEmail() == null || kho.getEmail().isEmpty()) {
            // Trường email không được trống
            APIResponse response = new APIResponse(false, null, "Email không được trống");
            return response;
        }
    
        if (kho.getSDT() == null || kho.getSDT().isEmpty()) {
            // Trường số điện thoại không được trống
            APIResponse response = new APIResponse(false, null, "Số điện thoại không được trống");
            return response;
        }
    
        if (kho.getAddress() == null || kho.getAddress().isEmpty()) {
            // Trường địa chỉ không được trống
            APIResponse response = new APIResponse(false, null, "Địa chỉ không được trống");
            return response;
        }
    
        //thêm kho vào cơ sở dữ liệu
        khoService.createKho(kho);
    
        APIResponse response = new APIResponse(true, kho, "Kho đã được thêm thành công");
    
        return response;
    }
    @DeleteMapping("/xoa/{id}")
    public APIResponse deleteKho(@PathVariable Long id) {
        khoService.deleteKho(id);

        APIResponse response = new APIResponse(true, null, "Kho đã được xóa thành công");

        return response;
    }
    @PutMapping("/sua/{id}")
    public APIResponse suaKho(@PathVariable long id, @RequestBody Kho kho) {
        try{
        Kho updatekho = khoService.getKhoById(id);
            if(updatekho==null) {
                APIResponse response = new APIResponse(false, null, "Kho khong co");

                return response;
            }else {
                if (kho.getTenKho() != null) {
                    updatekho.setTenKho(kho.getTenKho());
                }
                if (kho.getAddress() != null) {
                    updatekho.setAddress(kho.getAddress());
                }

                if (kho.getSDT() != null) {
                    updatekho.setSDT(kho.getSDT());
                }

                if (kho.getEmail() != null) {
                    updatekho.setEmail(kho.getEmail());
                }


                khoService.updateKho(id, updatekho);
                APIResponse response = new APIResponse(true, updatekho, "ok");
                return response;
            }
        }
        catch (Exception e) {
            APIResponse response = new APIResponse(false, e, null);
            return response;
        }
}
}
