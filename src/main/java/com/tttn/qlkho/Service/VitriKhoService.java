package com.tttn.qlkho.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.Model.Vitrikho;
import com.tttn.qlkho.Repository.ViTriKhoRepository;

@Service
public class VitriKhoService {
    @Autowired
    private ViTriKhoRepository VTKhorepo;

    public List<Vitrikho> getAllVitriKho(){
        return  VTKhorepo.findAll();
    }

    public Vitrikho getvtkhoById(Long id) {
        return VTKhorepo.findById(id).orElse(null);
    }
    public Vitrikho createvtkho(Vitrikho vtkho) {
        return VTKhorepo.save(vtkho);
    }
    public Vitrikho updatevtkho(Long id, Vitrikho vtkho) {
        return VTKhorepo.save(vtkho);
    }
    public void deletevtkho(Long id) {
        VTKhorepo.deleteById(id);
    }
    // public Kho getKhoByName(String a) {
    //     return Khorepo.findByTenKho(a);
    // }
}
