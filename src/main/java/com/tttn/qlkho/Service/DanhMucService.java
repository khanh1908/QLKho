package com.tttn.qlkho.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.Model.DanhMuc;
import com.tttn.qlkho.Ropository.DanhMucRepository;

@Service
public class DanhMucService {
    @Autowired
    private DanhMucRepository DanhMuc;

    public List<DanhMuc> getAllDanhMuc(){
        return  DanhMuc.findAll();
    }
    public DanhMuc getdanhMucById(Long id) {
        return DanhMuc.findById(id).orElse(null);
    }
    public DanhMuc createdanhMuc(DanhMuc danhMuc) {
        return DanhMuc.save(danhMuc);
    }
    public DanhMuc updatedanhMuc(Long id, DanhMuc danhMuc) {
        return DanhMuc.save(danhMuc);
    }
    public void deletedanhMuc(Long id) {
        DanhMuc.deleteById(id);
    }
    // public DanhMuc getKhoByName(String a) {
    //     return DanhMuc.findByTenKho(a);
    // }
}
