package com.tttn.qlkho.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.Model.NhaCungCap;
import com.tttn.qlkho.Ropository.NhaCungCapRepository;

@Service
public class NhaCungCapService {
    @Autowired
    private NhaCungCapRepository NhaCungCaprepo;

    public List<NhaCungCap> getAllNhacungCap(){
        return  NhaCungCaprepo.findAll();
    }

    public NhaCungCap getNhacungCapById(Long id) {
        return NhaCungCaprepo.findById(id).orElse(null);
    }
    public NhaCungCap createNhacungCap(NhaCungCap ncc) {
        return NhaCungCaprepo.save(ncc);
    }
    public NhaCungCap updateNhacungCap(Long id, NhaCungCap ncc) {
        return NhaCungCaprepo.save(ncc);
    }
    public void deleteNcc(Long id) {
        NhaCungCaprepo.deleteById(id);
    }
    // public Kho getKhoByName(String a) {
    //     return Khorepo.findByTenKho(a);
    // }
}
