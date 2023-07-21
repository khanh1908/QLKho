package com.tttn.qlkho.Service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.Ropository.PhieuNhapRepository;

@Service
public class PhieuNhapService {
    private PhieuNhapRepository phieunhapRepo;

    @Autowired
    public PhieuNhapService(PhieuNhapRepository phieunhapRepo) {
        this.phieunhapRepo = phieunhapRepo;
    }

    public String taoPhieuNhapSanPham(String tenSanPham, BigDecimal gia, String tenDanhMuc,
                                      String tenNhaCungCap, int soLuong) {
        System.out.println("AAAAAAAA" + tenDanhMuc + tenNhaCungCap + gia + soLuong + tenSanPham);
        return phieunhapRepo.taoPhieuNhapSanPham(tenSanPham, gia, tenDanhMuc, tenNhaCungCap, soLuong);
    }
}