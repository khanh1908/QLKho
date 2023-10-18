package com.tttn.qlkho.Controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tttn.qlkho.DTO.AddSanPham;
import com.tttn.qlkho.DTO.DoanhThu;
import com.tttn.qlkho.DTO.PhieuNhapRequest;
import com.tttn.qlkho.DTO.ThongKeSanPhamNhap;
import com.tttn.qlkho.DTO.ThongKeTienNhap;
import com.tttn.qlkho.Model.CTPN;
import com.tttn.qlkho.Model.Kho;
import com.tttn.qlkho.Model.NhaCungCap;
import com.tttn.qlkho.Model.PhieuNhap;
import com.tttn.qlkho.Model.SanPham;
import com.tttn.qlkho.Model.Vitrikho;
import com.tttn.qlkho.Response.APIResponse;
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

    @GetMapping()
    public APIResponse getAllPhieuNhap() {
        List<PhieuNhap> PhieuNhaplist = phieuNhapService.getAllPN();
        APIResponse response = new APIResponse(true, PhieuNhaplist, "Danh sách phieu nhap đã được lấy thành công");
        return response;
    }

    @GetMapping("/ctpn/{idPhieuNhap}")
    public List<CTPN> getCTPNByIDPN(@PathVariable Long idPhieuNhap) {
        return ctpnService.getCTPNByIDPN(idPhieuNhap);
    }
    @PostMapping("/create")
    public APIResponse createPhieuNhap(@RequestBody PhieuNhapRequest request) {
        // Lấy thông tin từ DTO
        Long nhaCungCapId = request.getNhaCungCapId();
        Long userId = request.getUserId();
        List<CTPN> chiTietPhieuNhapList = request.getChiTietPhieuNhapList();
        // List<Vitrikho> viTriKhoList = request.getViTriKhoList();
        // Long VitrikhoId = request.getVitrikhoId();
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
        // Vitrikho vitrikho = new Vitrikho();
        for (CTPN chiTietPhieuNhap : chiTietPhieuNhapList) {
            // Set thông tin từ DTO vào chi tiết phiếu nhập
            chiTietPhieuNhap.setPhieunhap(phieuNhap);
            SanPham sanpham = sanPhamService.getSanPhamById(chiTietPhieuNhap.getSanpham().getId());
            chiTietPhieuNhap.setSanpham(sanpham);
            ctpnService.createCTPN(chiTietPhieuNhap);
            Vitrikho vitrikho = sanpham.getVitrikho();
            if(vitrikho != null && vitrikho.getSoLuong() >= (sanpham.getSoLuong() + chiTietPhieuNhap.getSoluong())){
                sanpham.setSoLuong(sanpham.getSoLuong() + chiTietPhieuNhap.getSoluong());
                sanPhamService.updateSanPhamSl(sanpham);
                APIResponse response = new APIResponse(true, phieuNhap, "Tao phieu nhap thanh cong");
                return response;
            }
            else{
                APIResponse response = new APIResponse(false, phieuNhap, "Vi tri " + sanpham.getTenSanPham() + " con lai " + (vitrikho.getSoLuong()- sanpham.getSoLuong()) );
                return response;
            }
            
                // APIResponse response = new APIResponse(true, viTriKho.getSoLuong(), "san pham da duoc them vao kho");
                // return response;
            }
            // Vitrikho viTriKho = vitriKhoService.getViTriKhoBySanPhamAndKho(sanpham, kho);
            // System.out.println("aaaaaaaaaaaaaa" + viTriKho);
            // viTriKho.setSoLuong(viTriKho.getSoLuong() + chiTietPhieuNhap.getSoluong());
            // vitriKhoService.updatevitrikho(viTriKho);
        APIResponse response = new APIResponse(true, phieuNhap, "ok");
        return response;
    }

    @GetMapping("/soluongsanpham")
    public APIResponse thongKeSoLuongSanPhamController() {
        List<ThongKeSanPhamNhap> resultList = ctpnService.thongKeSoLuongSanPhamNhap();
        APIResponse response = new APIResponse(true, resultList, "Thong ke so luong san pham nhap");
        return response;
    }

     @GetMapping("/tongtiennhap")
    public APIResponse thongKeTongTienNhapSanPhamController() {
        List<ThongKeTienNhap> resultList = ctpnService.thongKeTongTienNhapSanPham();
        APIResponse response = new APIResponse(true, resultList, "Thong ke tong tien nhap");
        return response;
    }

    // @GetMapping("/doanhthu")
    // public ResponseEntity<List<DoanhThu>> thongKeDoanhThuTheoNgay(
    //         @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
    //         @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
    //     List<DoanhThu> doanhThuList = ctpnService.thongKeDoanhThuSanPhamTheoNgay(fromDate, toDate);
    //     return ResponseEntity.ok(doanhThuList);
    // }


// @GetMapping("/tongtiennhap")
//     public APIResponse thongKeTongTienNhapSanPham() {
//         Map<PhieuNhap, BigDecimal> thongKe = ctpnService.thongKeTongTienNhapSanPham();
        
//         // Chuyển đổi Map thành JSON
//         ObjectMapper objectMapper = new ObjectMapper();
//         String jsonString;
//         try {
//             jsonString = objectMapper.writeValueAsString(thongKe);
//         } catch (JsonProcessingException e) {
//             e.printStackTrace();
//             // Xử lý lỗi nếu cần
//             return new APIResponse(false, null, "Lỗi khi chuyển đổi dữ liệu sang JSON");
//         }

//         APIResponse response = new APIResponse(true, jsonString, "Thong ke tong tien nhap");
//         return response;
    // }
}
