package com.tttn.qlkho.DTO;

import java.math.BigDecimal;

import com.tttn.qlkho.Model.PhieuXuat;

import lombok.Data;
@Data
public class ThongKeTienXuat {
    private PhieuXuat phieuxuat;
    private BigDecimal tongTienXuat;
    public ThongKeTienXuat(PhieuXuat phieuxuat, BigDecimal tongTienXuat) {
        this.phieuxuat = phieuxuat;
        this.tongTienXuat = tongTienXuat;
    }

}
