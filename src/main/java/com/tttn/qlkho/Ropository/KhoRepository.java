package com.tttn.qlkho.Ropository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.Kho;

@Repository
public interface KhoRepository extends JpaRepository<Kho, Long>{

    Kho findByTenKho(String TenKho);
    // User findByuserName(String userName);
}
