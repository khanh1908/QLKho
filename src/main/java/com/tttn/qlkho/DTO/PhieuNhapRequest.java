package com.tttn.qlkho.DTO;

import java.util.List;

import com.tttn.qlkho.Model.CTPN;
import com.tttn.qlkho.Model.Vitrikho;

import lombok.Data;

@Data
public class PhieuNhapRequest {
    private Long nhaCungCapId;
    private Long userId;
    private List<CTPN> chiTietPhieuNhapList;
    private List<Vitrikho> viTriKhoList;
    private Long khoId;
    // private Long sanphamId;
}
