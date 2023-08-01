package com.tttn.qlkho.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.SanPham;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham,Long>{
    
}
