package com.tttn.qlkho.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.Model.DanhMuc;
import com.tttn.qlkho.Repository.DanhMucRepository;

@Service
public class DanhMucService {
    @Autowired
    private DanhMucRepository DanhMucRepository;

    public List<DanhMuc> getAllDanhMuc(){
        return  DanhMucRepository.findAll();
    }
    public DanhMuc getdanhMucById(Long id) {
        return DanhMucRepository.findById(id).orElse(null);
    }
    public DanhMuc createdanhMuc(DanhMuc danhMuc) {
        return DanhMucRepository.save(danhMuc);
    }
    public DanhMuc updatedanhMuc(Long id, DanhMuc danhMuc) {
        return DanhMucRepository.save(danhMuc);
    }
    public void deletedanhMuc(Long id) {
        DanhMucRepository.deleteById(id);
    }
    // public DanhMuc getKhoByName(String a) {
    //     return DanhMuc.findByTenKho(a);
    // }
}
