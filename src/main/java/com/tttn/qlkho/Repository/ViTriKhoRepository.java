package com.tttn.qlkho.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.Kho;
import com.tttn.qlkho.Model.SanPham;
import com.tttn.qlkho.Model.Vitrikho;

@Repository
public interface ViTriKhoRepository extends JpaRepository<Vitrikho, Long>{
    // Vitrikho findByKhoAndSp(Kho kho, SanPham sanPham);
    // Vitrikho findBySpAndKho(SanPham sanPham, Kho kho);
    // boolean existsBySpAndKho(SanPham sanpham, Kho kho);
    List<Vitrikho> findByKhoId(long khoId);
    // List<Vitrikho> findBySpId(long spId);
}
