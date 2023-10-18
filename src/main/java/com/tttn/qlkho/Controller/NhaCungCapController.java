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

import com.tttn.qlkho.Model.NhaCungCap;
import com.tttn.qlkho.Response.APIResponse;
import com.tttn.qlkho.Service.NhaCungCapService;

@RestController
@RequestMapping("/NhaCungCap")
// @PreAuthorize("hasRole('ADMIN')")
public class NhaCungCapController {
    @Autowired
    NhaCungCapService NhacungcapService;

    @GetMapping()
    public APIResponse getAllNhaCungCap() {
        List<NhaCungCap> nhacungcaplist = NhacungcapService.getAllNhacungCap();
        APIResponse response = new APIResponse(true, nhacungcaplist, "Danh sách nha cun cap đã được lấy thành công");
        return response;
    }
    @PostMapping("/them")
    public APIResponse createNhaCungCap(@RequestBody NhaCungCap nhacungcap) {
        NhacungcapService.createNhacungCap(nhacungcap);
        APIResponse response = new APIResponse(true, nhacungcap, "nha cung cap đã được thêm thành công");

        return response;
    }
    @DeleteMapping("/xoa/{id}")
    public APIResponse deleteNhacungcap(@PathVariable Long id) {
        NhacungcapService.deleteNcc(id);
        APIResponse response = new APIResponse(true, null, "nha cung cap đã được xóa thành công");

        return response;
    }
    @PutMapping("/sua/{id}")
    public APIResponse SuaNhaCungCap(@PathVariable long id, @RequestBody NhaCungCap nhacungcap) {
        try{
        NhaCungCap updateNcc = NhacungcapService.getNhacungCapById(id);
            if(updateNcc==null) {
                APIResponse response = new APIResponse(false, null, "nha cung cap khong co");

                return response;
            }else {
                if (nhacungcap.getTenNCC() != null) {
                    updateNcc.setTenNCC(nhacungcap.getTenNCC());
                }
                if (nhacungcap.getAddress() != null) {
                    updateNcc.setAddress(nhacungcap.getAddress());
                }

                if (nhacungcap.getSDT() != null) {
                    updateNcc.setSDT(nhacungcap.getSDT());
                }

                if (nhacungcap.getEmail() != null) {
                    updateNcc.setEmail(nhacungcap.getEmail());
                }


                NhacungcapService.updateNhacungCap(id, updateNcc);
                APIResponse response = new APIResponse(true, updateNcc, "ok");
                return response;
            }
        }
        catch (Exception e) {
            APIResponse response = new APIResponse(false, e, null);
            return response;
        }
}
}
