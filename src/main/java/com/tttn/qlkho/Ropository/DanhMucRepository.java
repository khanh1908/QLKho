package com.tttn.qlkho.Ropository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.DanhMuc;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, Long>{
    
}
