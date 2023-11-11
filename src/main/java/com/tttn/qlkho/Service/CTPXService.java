package com.tttn.qlkho.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.DTO.ThongKeSanPhamNhap;
import com.tttn.qlkho.DTO.ThongKeTienNhap;
import com.tttn.qlkho.DTO.ThongKeTienXuat;
import com.tttn.qlkho.Model.CTPN;
import com.tttn.qlkho.Model.CTPX;
import com.tttn.qlkho.Model.PhieuNhap;
import com.tttn.qlkho.Model.PhieuXuat;
import com.tttn.qlkho.Model.SanPham;
import com.tttn.qlkho.Repository.CTPNRepository;
import com.tttn.qlkho.Repository.CTPXRepository;

@Service
public class CTPXService {
    @Autowired
    private CTPXRepository CTPXrepository;

    public List<CTPX> getAllCTPX(){
        return  CTPXrepository.findAll();
    }
    public CTPX getCTPXById(Long id) {
        return CTPXrepository.findById(id).orElse(null);
    }
    public CTPX createCTPX(CTPX chiTietPhieuXuat) {
        return CTPXrepository.save(chiTietPhieuXuat);
    }
    public CTPX updateCTPX(Long id, CTPX ctpx) {
        return CTPXrepository.save(ctpx);
    }
    public void deleteCTPX(Long id) {
        CTPXrepository.deleteById(id);
    }

    public List<CTPX> getCTPNByIDPX(Long idphieuxuat) {
        return CTPXrepository.findByPhieuxuat_Id(idphieuxuat);
    }
    public List<ThongKeSanPhamNhap> thongKeSoLuongSanPhamXuat() {
        List<CTPX> chiTietPhieuXuat = CTPXrepository.findAll();
        
        Map<SanPham, Integer> thongKe = new HashMap<>();
        for (CTPX chiTiet : chiTietPhieuXuat) {
            SanPham sanPham = chiTiet.getSanpham();
            int soLuong = chiTiet.getSoluong();
            
            thongKe.put(sanPham, thongKe.getOrDefault(sanPham, 0) + soLuong);
        }
    
        List<ThongKeSanPhamNhap> resultList = new ArrayList<>();
        for (Map.Entry<SanPham, Integer> entry : thongKe.entrySet()) {
            resultList.add(new ThongKeSanPhamNhap(entry.getKey(), entry.getValue()));
        }
        
        return resultList;
    }

    public List<ThongKeTienXuat> thongKeTongTienXuatSanPham() {
        List<CTPX> chiTietPhieuXuat = CTPXrepository.findAll();
        
        Map<PhieuXuat, BigDecimal> thongKe = new HashMap<>();
        for (CTPX chiTiet : chiTietPhieuXuat) {
            PhieuXuat phieuXuat = chiTiet.getPhieuxuat();
            BigDecimal tongTien = thongKe.getOrDefault(phieuXuat, BigDecimal.ZERO);
            BigDecimal donGia = chiTiet.getGia();
            int soLuong = chiTiet.getSoluong();
            BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(soLuong));
            
            thongKe.put(phieuXuat, tongTien.add(thanhTien));
        }
    
        List<ThongKeTienXuat> resultList = new ArrayList<>();
        for (Map.Entry<PhieuXuat, BigDecimal> entry : thongKe.entrySet()) {
            resultList.add(new ThongKeTienXuat(entry.getKey(), entry.getValue()));
        }
        
        return resultList;
    }
}
