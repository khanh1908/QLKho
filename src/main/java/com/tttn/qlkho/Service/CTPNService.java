package com.tttn.qlkho.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.qlkho.DTO.DoanhThu;
import com.tttn.qlkho.DTO.ThongKeSanPhamNhap;
import com.tttn.qlkho.DTO.ThongKeTienNhap;
import com.tttn.qlkho.Model.CTPN;
import com.tttn.qlkho.Model.CTPX;
import com.tttn.qlkho.Model.PhieuNhap;
import com.tttn.qlkho.Model.PhieuXuat;
import com.tttn.qlkho.Model.SanPham;
import com.tttn.qlkho.Repository.CTPNRepository;
import com.tttn.qlkho.Repository.CTPXRepository;

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
    public List<CTPN> getCTPNByIDPN(Long idPhieuNhap) {
        return CTPNrepository.findByPhieunhap_Id(idPhieuNhap);
    }

    public List<ThongKeSanPhamNhap> thongKeSoLuongSanPhamNhap() {
        List<CTPN> chiTietPhieuNhaps = CTPNrepository.findAll();
        
        Map<SanPham, Integer> thongKe = new HashMap<>();
        for (CTPN chiTiet : chiTietPhieuNhaps) {
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

    public List<ThongKeTienNhap> thongKeTongTienNhapSanPham() {
        List<CTPN> chiTietPhieuNhaps = CTPNrepository.findAll();
        
        // Thực hiện thống kê tổng tiền nhập sản phẩm theo phiếu nhập
        Map<PhieuNhap, BigDecimal> thongKe = new HashMap<>();
        for (CTPN chiTiet : chiTietPhieuNhaps) {
            PhieuNhap phieuNhap = chiTiet.getPhieunhap();
            BigDecimal tongTien = thongKe.getOrDefault(phieuNhap, BigDecimal.ZERO);
            BigDecimal donGia = chiTiet.getGia();
            int soLuong = chiTiet.getSoluong();
            BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(soLuong));
            
            thongKe.put(phieuNhap, tongTien.add(thanhTien));
        }
        List<ThongKeTienNhap> valuesList = new ArrayList<>();
        for (Map.Entry<PhieuNhap, BigDecimal> entry : thongKe.entrySet()) {
            valuesList.add(new ThongKeTienNhap(entry.getKey(), entry.getValue()));
        }
        return valuesList;
    }

    // public List<DoanhThu> thongKeDoanhThuSanPhamTheoNgay(LocalDate fromDate, LocalDate toDate) {
    //     List<CTPN> chiTietPhieuNhaps = CTPNrepository.findByNgayNhapBetween(fromDate, toDate);
    //     List<CTPX> chiTietPhieuXuat = CTPXRepository.findByNgayXuatBetween(fromDate, toDate);
    
    //     // Thực hiện thống kê tổng tiền nhập sản phẩm theo phiếu nhập
    //     Map<SanPham, BigDecimal> thongKeNhap = new HashMap<>();
    //     for (CTPN chiTiet : chiTietPhieuNhaps) {
    //         SanPham sanPham = chiTiet.getSanpham();
    //         BigDecimal tongTien = thongKeNhap.getOrDefault(sanPham, BigDecimal.ZERO);
    //         BigDecimal donGia = chiTiet.getGia();
    //         int soLuong = chiTiet.getSoluong();
    //         BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(soLuong));
    
    //         thongKeNhap.put(sanPham, tongTien.add(thanhTien));
    //     }
    
    //     // Thực hiện thống kê tổng tiền xuất sản phẩm theo phiếu xuất
    //     Map<SanPham, BigDecimal> thongKeXuat = new HashMap<>();
    //     for (CTPX chiTiet : chiTietPhieuXuat) {
    //         SanPham sanPham = chiTiet.getSanpham();
    //         BigDecimal tongTien = thongKeXuat.getOrDefault(sanPham, BigDecimal.ZERO);
    //         BigDecimal donGia = chiTiet.getGia();
    //         int soLuong = chiTiet.getSoluong();
    //         BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(soLuong));
    
    //         thongKeXuat.put(sanPham, tongTien.add(thanhTien));
    //     }
    
    //     // Tạo danh sách kết quả thống kê doanh thu
    //     List<DoanhThu> resultList = new ArrayList<>();
    //     for (Map.Entry<SanPham, BigDecimal> entry : thongKeNhap.entrySet()) {
    //         SanPham sanPham = entry.getKey();
    //         BigDecimal tongTienNhap = entry.getValue();
    
    //         BigDecimal tongTienXuat = thongKeXuat.getOrDefault(sanPham, BigDecimal.ZERO);
    
    //         BigDecimal doanhThu = tongTienXuat.subtract(tongTienNhap);
    
    //         resultList.add(new DoanhThu(sanPham, doanhThu));
    //     }
    
    //     return resultList;
    // }

}
