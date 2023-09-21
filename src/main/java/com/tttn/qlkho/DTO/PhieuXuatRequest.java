package com.tttn.qlkho.DTO;

import java.util.List;

import com.tttn.qlkho.Model.CTPX;

import lombok.Data;

@Data
public class PhieuXuatRequest {
    private Long userId;
    private List<CTPX> chiTietPhieuXuatList;
}
