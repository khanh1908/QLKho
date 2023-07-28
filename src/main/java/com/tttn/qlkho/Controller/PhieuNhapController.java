package com.tttn.qlkho.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tttn.qlkho.DTO.AddSanPham;
import com.tttn.qlkho.Service.PhieuNhapService;

@RestController
@RequestMapping("/phieunhap")
public class PhieuNhapController {
    @Autowired
    private PhieuNhapService phieuNhapService;
    @PostMapping("/tao-phieu-nhap")
    public ResponseEntity<String> taoPhieuNhapSanPham(@RequestBody AddSanPham request) {
        String result = phieuNhapService.taoPhieuNhapSanPham(request.getTenSanPham(), 
                                                            request.getGia(),
                                                            request.getSoLuong());
                    System.out.println("BBBBBBBBBBB"+result);
        return ResponseEntity.ok(result);
    }
}
