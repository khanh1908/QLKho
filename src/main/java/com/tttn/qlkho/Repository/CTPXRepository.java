package com.tttn.qlkho.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.CTPX;

@Repository
public interface CTPXRepository extends JpaRepository<CTPX,Long>{
    
}
