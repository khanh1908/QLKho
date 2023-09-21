package com.tttn.qlkho.DTO;

import java.util.List;

import com.tttn.qlkho.Model.CTPN;

import lombok.Data;

@Data
public class PhieuNhapRequest {
    private Long nhaCungCapId;
    private Long userId;
    private List<CTPN> chiTietPhieuNhapList;
    // private Long sanphamId;
}
