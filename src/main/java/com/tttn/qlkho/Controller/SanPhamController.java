package com.tttn.qlkho.Controller;

import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tttn.qlkho.Model.DanhMuc;
import com.tttn.qlkho.Model.SanPham;
import com.tttn.qlkho.Response.APIResponse;
import com.tttn.qlkho.Service.SanPhamService;
import com.tttn.qlkho.ZXingHelper.ZXingHelper;


@RestController
@RequestMapping("/Sanpham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanphamService;

    @GetMapping()
    public APIResponse getAllDanhMuc() {
        List<SanPham> sanphamlist = sanphamService.getAllSanPham();
        APIResponse response = new APIResponse(true, sanphamlist, "Danh sách san pham đã được lấy thành công");
        return response;
    }
    @PostMapping("/them")
    public APIResponse createDanhMuc(@RequestBody SanPham sanpham) {
        sanpham.setTrangThai("Còn Hạn");
        sanphamService.createSanPham(sanpham);
        APIResponse response = new APIResponse(true, sanpham, "danh muc đã được thêm thành công");
        return response;
    }
    @DeleteMapping("/xoa/{id}")
    public APIResponse deletedanhmuc(@PathVariable Long id) {
        
        sanphamService.deleteSanPham(id);

        APIResponse response = new APIResponse(true, null, "danh muc đã được xóa thành công");

        return response;
    }
    @GetMapping("/barcode/{id}")
	public void barcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception
	{
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getBarCodeImage(id, 200, 50));
		outputStream.flush();
		outputStream.close();
	}

    @GetMapping("/{id}")
    public APIResponse getSanPhamDetailById(@PathVariable long id){

        try {
            SanPham sanpham = sanphamService.getSanPhamById(id);
            return new APIResponse(true, sanpham, "Tìm kiếm san pham thanh cong: "+id);
        } catch (Exception e) {
            return new APIResponse(false, null, "tim kiem khong thanh cong");
        }
    }

    @GetMapping("/byvitrikho/{vitrikhoId}")
    public List<SanPham> getSanPhamByVitrikhoId(@PathVariable long vitrikhoId) {
        return sanphamService.getSanPhamByVitrikhoId(vitrikhoId);
    }

    @PutMapping("/sua/{id}")
    public APIResponse suadanhmuc(@PathVariable("id") long id, @RequestBody SanPham sanPham) {
        try{
        SanPham updateSanpham = sanphamService.getSanPhamById(id);
            if(updateSanpham==null) {
                APIResponse response = new APIResponse(false, null, "danh muc khong co");
                return response;
            }else {
                if (sanPham.getTenSanPham() != null) {
                    updateSanpham.setTenSanPham(sanPham.getTenSanPham());
                }
                if (sanPham.getDanhmuc() != null) {
                    updateSanpham.setDanhmuc(sanPham.getDanhmuc());
                }
                if (sanPham.getHanSuDung() != null) {
                    updateSanpham.setHanSuDung(sanPham.getHanSuDung());
                }
                if (sanPham.getNgaySanXuat() != null) {
                    updateSanpham.setNgaySanXuat(sanPham.getNgaySanXuat());
                }
                sanphamService.updateSanPham(id, updateSanpham);
                APIResponse response = new APIResponse(true, updateSanpham, "ok");
                return response;
            }
        }
        catch (Exception e) {
            APIResponse response = new APIResponse(false, e, null);
            return response;
        }
}
}