package com.tttn.qlkho.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.CTPN;

@Repository
public interface CTPNRepository extends JpaRepository<CTPN,Long>{
    
}
