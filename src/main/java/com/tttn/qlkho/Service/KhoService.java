package com.tttn.qlkho.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.Model.Kho;
import com.tttn.qlkho.Ropository.KhoRepository;

@Service
public class KhoService {
    @Autowired
    private KhoRepository Khorepo;

    public List<Kho> getAllKho(){
        return  Khorepo.findAll();
    }

    public Kho getKhoById(Long id) {
        return Khorepo.findById(id).orElse(null);
    }
    public Kho createKho(Kho kho) {
        return Khorepo.save(kho);
    }
    public Kho updateKho(Long id, Kho kho) {
        return Khorepo.save(kho);
    }
    public void deleteKho(Long id) {
        Khorepo.deleteById(id);
    }
    public Kho getKhoByName(String a) {
        return Khorepo.findByTenKho(a);
    }
}
