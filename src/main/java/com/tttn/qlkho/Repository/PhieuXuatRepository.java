package com.tttn.qlkho.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.PhieuXuat;

@Repository
public interface PhieuXuatRepository extends JpaRepository<PhieuXuat,Long>{
    List<PhieuXuat> findByTrangthai(int trangthai);
}
