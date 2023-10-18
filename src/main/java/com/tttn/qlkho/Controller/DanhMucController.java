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

import com.tttn.qlkho.Model.DanhMuc;
import com.tttn.qlkho.Response.APIResponse;
import com.tttn.qlkho.Service.DanhMucService;

@RestController
@RequestMapping("/Danhmuc")
// @PreAuthorize("hasRole('ADMIN')")
public class DanhMucController {
    @Autowired
    DanhMucService danhmucservice;

    @GetMapping()
    public APIResponse getAllDanhMuc() {
        List<DanhMuc> danhmuclist = danhmucservice.getAllDanhMuc();
        APIResponse response = new APIResponse(true, danhmuclist, "Danh sách danh muc đã được lấy thành công");
        return response;
    }
    @PostMapping("/them")
    public APIResponse createDanhMuc(@RequestBody DanhMuc danhmuc) {
        danhmucservice.createdanhMuc(danhmuc);
        APIResponse response = new APIResponse(true, danhmuc, "danh muc đã được thêm thành công");

        return response;
    }
    @DeleteMapping("/xoa/{id}")
    public APIResponse deletedanhmuc(@PathVariable Long id) {
        danhmucservice.deletedanhMuc(id);
        APIResponse response = new APIResponse(true, null, "danh muc đã được xóa thành công");

        return response;
    }
    @PutMapping("/sua/{id}")
    public APIResponse suadanhmuc(@PathVariable long id, @RequestBody DanhMuc danhmuc) {
        try{
        DanhMuc updatedanhmuc = danhmucservice.getdanhMucById(id);
            if(updatedanhmuc==null) {
                APIResponse response = new APIResponse(false, null, "danh muc khong co");

                return response;
            }else {
                if (danhmuc.getTenDM() != null) {
                    updatedanhmuc.setTenDM(danhmuc.getTenDM());
                }
                danhmucservice.updatedanhMuc(id, updatedanhmuc);
                APIResponse response = new APIResponse(true, updatedanhmuc, "ok");
                return response;
            }
        }
        catch (Exception e) {
            APIResponse response = new APIResponse(false, e, null);
            return response;
        }
}
}
