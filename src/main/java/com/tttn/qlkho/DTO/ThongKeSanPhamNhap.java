package com.tttn.qlkho.DTO;

import com.tttn.qlkho.Model.SanPham;

import lombok.Data;
@Data
public class ThongKeSanPhamNhap {
    private SanPham sanPham;
    private Integer soLuong;

    public ThongKeSanPhamNhap(SanPham sanPham, Integer soLuongNhap) {
        this.sanPham = sanPham;
        this.soLuong = soLuongNhap;
    }
}
