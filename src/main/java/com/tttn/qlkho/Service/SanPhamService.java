package com.tttn.qlkho.Service;

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
    public SanPham createNhacungCap(SanPham sp) {
        return sanPhamRepository.save(sp);
    }
    public SanPham updateNhacungCap(Long id, SanPham sp) {
        return sanPhamRepository.save(sp);
    }
    public void deleteNcc(Long id) {
        sanPhamRepository.deleteById(id);
    }
    // public Kho getKhoByName(String a) {
    //     return Khorepo.findByTenKho(a);
    // }
}
