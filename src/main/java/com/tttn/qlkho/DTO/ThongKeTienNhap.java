package com.tttn.qlkho.DTO;

import java.math.BigDecimal;

import com.tttn.qlkho.Model.PhieuNhap;

import lombok.Data;
@Data
public class ThongKeTienNhap {
    private PhieuNhap phieuNhap;
    private BigDecimal tongTienNhap;
    public ThongKeTienNhap(PhieuNhap phieuNhap, BigDecimal tongTienNhap) {
        this.phieuNhap = phieuNhap;
        this.tongTienNhap = tongTienNhap;
    }

}
