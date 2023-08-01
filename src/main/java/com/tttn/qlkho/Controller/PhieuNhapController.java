package com.tttn.qlkho.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tttn.qlkho.DTO.AddSanPham;
import com.tttn.qlkho.DTO.PhieuNhapRequest;
import com.tttn.qlkho.Model.CTPN;
import com.tttn.qlkho.Model.Kho;
import com.tttn.qlkho.Model.PhieuNhap;
import com.tttn.qlkho.Model.SanPham;
import com.tttn.qlkho.Model.Vitrikho;
import com.tttn.qlkho.Service.CTPNService;
import com.tttn.qlkho.Service.KhoService;
import com.tttn.qlkho.Service.NhaCungCapService;
import com.tttn.qlkho.Service.PhieuNhapService;
import com.tttn.qlkho.Service.SanPhamService;
import com.tttn.qlkho.Service.UserDetailService;
import com.tttn.qlkho.Service.VitriKhoService;

@RestController
@RequestMapping("/phieunhap")
public class PhieuNhapController {
    @Autowired
    private PhieuNhapService phieuNhapService;

    @Autowired
    private KhoService khoservice;

    @Autowired
    private NhaCungCapService nhaCungCapService;

    @Autowired
    private UserDetailService userservice;

    @Autowired
    private VitriKhoService vitriKhoService;

    @Autowired
    private CTPNService ctpnService;

    @Autowired
    private SanPhamService sanPhamService;
    
    // @PostMapping("/tao-phieu-nhap")
    // public ResponseEntity<String> taoPhieuNhapSanPham(@RequestBody AddSanPham request) {
    //     String result = phieuNhapService.taoPhieuNhapSanPham(request.getTenSanPham(), 
    //                                                         request.getGia(),
    //                                                         request.getSoLuong());
    //                 System.out.println("BBBBBBBBBBB"+result);
    //     return ResponseEntity.ok(result);
    // }

    @PostMapping("/create")
    public ResponseEntity<PhieuNhap> createPhieuNhap(@RequestBody PhieuNhapRequest request) {
        // Lấy thông tin từ DTO
        Long nhaCungCapId = request.getNhaCungCapId();
        Long userId = request.getUserId();
        List<CTPN> chiTietPhieuNhapList = request.getChiTietPhieuNhapList();
        List<Vitrikho> viTriKhoList = request.getViTriKhoList();
        Long khoId = request.getKhoId();
        // Long sanphamId = request.getSanphamId();

        // Tạo đối tượng Phiếu Nhập và set thông tin từ người dùng
        PhieuNhap phieuNhap = new PhieuNhap();
        Date currentDate = new Date(System.currentTimeMillis());
        phieuNhap.setNgayNhap(currentDate); // Hoặc bạn có thể lấy ngày nhập từ DTO nếu client gửi lên
        phieuNhap.setNhacungcap(nhaCungCapService.getNhacungCapById(nhaCungCapId));
        phieuNhap.setUser(userservice.getUserById(userId));
        // Lưu phiếu nhập
        phieuNhap = phieuNhapService.createPhieuNhap(phieuNhap);

        // Tạo chi tiết phiếu nhập và lưu thông tin

        // SanPham sanpham = sanPhamService.getSanPhamById(sanphamId);
        for (CTPN chiTietPhieuNhap : chiTietPhieuNhapList) {
            // Set thông tin từ DTO vào chi tiết phiếu nhập
            chiTietPhieuNhap.setPhieunhap(phieuNhap);
            SanPham sanpham = sanPhamService.getSanPhamById(chiTietPhieuNhap.getSanpham().getId());
            chiTietPhieuNhap.setSanpham(sanpham);
            ctpnService.createCTPN(chiTietPhieuNhap);
        }

        // Lưu vi trí kho
        Kho kho = khoservice.findById(khoId);
        for (Vitrikho viTriKho : viTriKhoList) {
            viTriKho.setKho(kho);
            // Lưu thông tin vi trí kho
            SanPham sanpham = sanPhamService.getSanPhamById(viTriKho.getSp().getId());
            viTriKho.setSp(sanpham);            // Lưu thông tin chi tiết phiếu nhập
            vitriKhoService.createvtkho(viTriKho);
        }

        return new ResponseEntity<>(phieuNhap, HttpStatus.CREATED);
    }

}
