package com.tttn.qlkho.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tttn.qlkho.Service.SanPhamService;

@Component
public class scheduler {
    @Autowired
    private SanPhamService sanPhamService;

    @Scheduled(fixedRate = 50000000)
    public void updateProductStatus() {
        sanPhamService.updateTrangThaiSp();
    }
}
