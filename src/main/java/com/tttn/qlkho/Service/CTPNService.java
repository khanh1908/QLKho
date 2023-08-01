package com.tttn.qlkho.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.Model.CTPN;
import com.tttn.qlkho.Repository.CTPNRepository;

@Service
public class CTPNService {
    @Autowired
    private CTPNRepository CTPNrepository;

    public List<CTPN> getAllCTPN(){
        return  CTPNrepository.findAll();
    }
    public CTPN getCTPNById(Long id) {
        return CTPNrepository.findById(id).orElse(null);
    }
    public CTPN createCTPN(CTPN ctpn) {
        return CTPNrepository.save(ctpn);
    }
    public CTPN updateCTPN(Long id, CTPN ctpn) {
        return CTPNrepository.save(ctpn);
    }
    public void deleteCTPN(Long id) {
        CTPNrepository.deleteById(id);
    }
}
