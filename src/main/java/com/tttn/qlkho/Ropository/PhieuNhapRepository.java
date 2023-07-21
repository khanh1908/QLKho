package com.tttn.qlkho.Ropository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.PhieuNhap;

@Repository
public interface PhieuNhapRepository extends JpaRepository<PhieuNhap, Long> {

    // @Procedure(name = "spTaoPhieuNhapSanPham")
    @Query(value = "CALL spTaoPhieuNhapSanPham(:pTenSanPham, :pGia, :pTenDanhMuc, :pTenNhaCungCap, :pSoLuong);", nativeQuery = true)
    String taoPhieuNhapSanPham(
            @Param("pTenSanPham") String tenSanPham,
            @Param("pGia") BigDecimal gia,
            @Param("pTenDanhMuc") String tenDanhMuc,
            @Param("pTenNhaCungCap") String tenNhaCungCap,
            @Param("pSoLuong") int soLuong
    );
}
