package com.tttn.qlkho.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.Model.SanPham;
import com.tttn.qlkho.Repository.SanPhamRepository;

@Service
public class SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;

    public List<SanPham> getAllSanPham(){
        return  sanPhamRepository.findAll();
    }

    public SanPham getSanPhamById(Long id) {
        return sanPhamRepository.findById(id).orElse(null);
    }
    public SanPham createSanPham(SanPham sp) {
        return sanPhamRepository.save(sp);
    }
    public SanPham updateSanPham(Long id, SanPham sp) {
        return sanPhamRepository.save(sp);
    }
    public SanPham updateSanPhamSl(SanPham sp) {
        return sanPhamRepository.save(sp);
    }
    public void deleteSanPham(Long id) {
        sanPhamRepository.deleteById(id);
    }

    public List<SanPham> getSanPhamByVitrikhoId(long vitrikhoId) {
        return sanPhamRepository.findByVitrikho_Id(vitrikhoId);
    }

    public void updateTrangThaiSp() {
        List<SanPham> sanPhams = sanPhamRepository.findAll();

        Date currentDate = new Date(System.currentTimeMillis());

        for (SanPham sanPham : sanPhams) {
            if (sanPham.getHanSuDung().before(currentDate)) {
                sanPham.setTrangThai("Hết hạn");
                sanPhamRepository.save(sanPham);
            }
        }
    }

    // public Kho getKhoByName(String a) {
    //     return Khorepo.findByTenKho(a);
    // }
}
