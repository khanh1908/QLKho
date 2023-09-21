package com.tttn.qlkho.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
