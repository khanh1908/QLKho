package com.tttn.qlkho.DTO;

import java.math.BigDecimal;

import com.tttn.qlkho.Model.SanPham;

import lombok.Data;
@Data
public class DoanhThu {
    private SanPham sanPham;
    private BigDecimal doanhThu;

    public DoanhThu(SanPham sanPham, BigDecimal doanhThu) {
        this.sanPham = sanPham;
        this.doanhThu = doanhThu;
    }
}
