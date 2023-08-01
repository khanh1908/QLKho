package com.tttn.qlkho.Service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.Model.PhieuNhap;
import com.tttn.qlkho.Repository.PhieuNhapRepository;

@Service
public class PhieuNhapService {
    // private PhieuNhapRepository phieunhapRepo;

    // @Autowired
    // public PhieuNhapService(PhieuNhapRepository phieunhapRepo) {
    //     this.phieunhapRepo = phieunhapRepo;
    // }

    // public String taoPhieuNhapSanPham(String tenSanPham, BigDecimal gia, int soLuong) {
    //     System.out.println("AAAAAAAA"  + gia + soLuong + tenSanPham);
    //     return phieunhapRepo.taoPhieuNhapSanPham(tenSanPham, gia, soLuong);
    // }

    @Autowired
    private PhieuNhapRepository phieunhaprepo;

    public PhieuNhap createPhieuNhap(PhieuNhap phieuNhap) {
        return phieunhaprepo.save(phieuNhap);
    }
}