package com.tttn.qlkho.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.Model.PhieuNhap;
import com.tttn.qlkho.Model.PhieuXuat;
import com.tttn.qlkho.Repository.PhieuXuatRepository;

@Service
public class PhieuXuatService {
    @Autowired
    private PhieuXuatRepository phieuXuatRepository;

    public PhieuXuat createPhieuXuat(PhieuXuat phieuXuat) {
        return phieuXuatRepository.save(phieuXuat);
    }
    public List<PhieuXuat> getAllPX(){
        return  phieuXuatRepository.findAll();
    }
    public List<PhieuXuat> getPhieuXuatByTrangThai(int trangthai) {
        return phieuXuatRepository.findByTrangthai(trangthai);
    }
    public Optional<PhieuXuat> updatePhieuXuatStatus(long id, int newStatus) {
        Optional<PhieuXuat> existingPhieuXuat = phieuXuatRepository.findById(id);
        existingPhieuXuat.ifPresent(phieuXuat -> {
            phieuXuat.setTrangthai(newStatus);
            phieuXuatRepository.save(phieuXuat);
        });
        return existingPhieuXuat;
    }
}
