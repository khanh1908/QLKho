package com.tttn.qlkho.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.Kho;

@Repository
public interface KhoRepository extends JpaRepository<Kho, Long>{

    Kho findByTenKho(String TenKho);
    Kho findByEmail(String Email);
    Kho findBySDT(String SDT);
    // User findByuserName(String userName);
}