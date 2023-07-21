package com.tttn.qlkho.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AddSanPham {
    private String tenSanPham;
    private BigDecimal gia;
    private String tenDanhMuc;
    private String tenNhaCungCap;
    private int soLuong;
}