package com.tttn.qlkho.Controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tttn.qlkho.DTO.PhieuNhapRequest;
import com.tttn.qlkho.DTO.PhieuXuatRequest;
import com.tttn.qlkho.DTO.ThongKeSanPhamNhap;
import com.tttn.qlkho.DTO.ThongKeTienNhap;
import com.tttn.qlkho.DTO.ThongKeTienXuat;
import com.tttn.qlkho.Model.CTPN;
import com.tttn.qlkho.Model.CTPX;
import com.tttn.qlkho.Model.Kho;
import com.tttn.qlkho.Model.PhieuNhap;
import com.tttn.qlkho.Model.PhieuXuat;
import com.tttn.qlkho.Model.SanPham;
import com.tttn.qlkho.Model.Vitrikho;
import com.tttn.qlkho.Response.APIResponse;
import com.tttn.qlkho.Service.CTPNService;
import com.tttn.qlkho.Service.CTPXService;
import com.tttn.qlkho.Service.KhoService;
import com.tttn.qlkho.Service.NhaCungCapService;
import com.tttn.qlkho.Service.PhieuNhapService;
import com.tttn.qlkho.Service.PhieuXuatService;
import com.tttn.qlkho.Service.SanPhamService;
import com.tttn.qlkho.Service.UserDetailService;
import com.tttn.qlkho.Service.VitriKhoService;

@RestController
@RequestMapping("/PhieuXuat")
public class PhieuXuatController {
    @Autowired
    private PhieuXuatService phieuXuatService;

    @Autowired
    private KhoService khoservice;

    @Autowired
    private NhaCungCapService nhaCungCapService;

    @Autowired
    private UserDetailService userservice;

    @Autowired
    private VitriKhoService vitriKhoService;

    @Autowired
    private CTPXService ctpxService;

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping()
    public APIResponse getAllPhieuNhap() {
        List<PhieuXuat> PhieuxuatList = phieuXuatService.getAllPX();
        APIResponse response = new APIResponse(true, PhieuxuatList, "Danh sách phieu nhap đã được lấy thành công");
        return response;
    }

    @GetMapping("/ctpx/{idPhieuXuat}")
    public List<CTPX> getCTPXByIDPX(@PathVariable Long idPhieuXuat) {
        return ctpxService.getCTPNByIDPX(idPhieuXuat);
    }

    @PostMapping("/create")
public APIResponse createPhieuXuat(@RequestBody PhieuXuatRequest request) {
    Long userId = request.getUserId();
    List<CTPX> chiTietPhieuXuatList = request.getChiTietPhieuXuatList();

    // Tạo đối tượng Phiếu Xuất và set thông tin từ người dùng
    PhieuXuat phieuXuat = new PhieuXuat();
    Date currentDate = new Date(System.currentTimeMillis());
    phieuXuat.setNgayxuat(currentDate);
    phieuXuat.setUser(userservice.getUserById(userId));

    // Lưu phiếu xuất
    phieuXuat = phieuXuatService.createPhieuXuat(phieuXuat);

    // Tạo chi tiết phiếu xuất và cập nhật thông tin vị trí kho
    Map<Long, CTPX> productQuantityMap = new HashMap<>();

    for (CTPX chiTietPhieuXuat : chiTietPhieuXuatList) {
        // Set thông tin từ DTO vào chi tiết phiếu xuất
        chiTietPhieuXuat.setPhieuxuat(phieuXuat);
        SanPham sanpham = sanPhamService.getSanPhamById(chiTietPhieuXuat.getSanpham().getId());
        chiTietPhieuXuat.setSanpham(sanpham);

        if (productQuantityMap.containsKey(sanpham.getId())) {
            // If yes, aggregate the quantity
            CTPX existingCTPX = productQuantityMap.get(sanpham.getId());
            existingCTPX.setSoluong(existingCTPX.getSoluong() + chiTietPhieuXuat.getSoluong());
        } else {
            // If not, add it to the map
            productQuantityMap.put(sanpham.getId(), chiTietPhieuXuat);
        }

        if (sanpham.getSoLuong() >= chiTietPhieuXuat.getSoluong()) {
            // No need to create CTPX here
            sanpham.setSoLuong(sanpham.getSoLuong() - chiTietPhieuXuat.getSoluong());
            sanPhamService.updateSanPhamSl(sanpham);
        } else {
            return new APIResponse(false, null, "Số lượng sản phẩm không đủ để xuất");
        }
    }

    // Create CTPX after processing the loop
    for (CTPX aggregatedCTPX : productQuantityMap.values()) {
        ctpxService.createCTPX(aggregatedCTPX);
    }

    APIResponse response = new APIResponse(true, phieuXuat, "Tạo phiếu xuất thành công");
    return response;
}

    @GetMapping("/soluongsanpham")
    public APIResponse thongKeSoLuongSanPhamController() {
        List<ThongKeSanPhamNhap> resultList = ctpxService.thongKeSoLuongSanPhamXuat();
        APIResponse response = new APIResponse(true, resultList, "Thong ke so luong san pham xuat");
        return response;
    }

    @GetMapping("/tongtienxuat")
    public APIResponse thongKeTongTienXuatSanPhamController() {
        List<ThongKeTienXuat> resultList = ctpxService.thongKeTongTienXuatSanPham();
        APIResponse response = new APIResponse(true, resultList, "Thong ke tong tien nhap");
        return response;
    }

}
