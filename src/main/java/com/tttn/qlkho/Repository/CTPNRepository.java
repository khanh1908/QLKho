package com.tttn.qlkho.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.CTPN;

@Repository
public interface CTPNRepository extends JpaRepository<CTPN,Long>{
    List<CTPN> findByPhieunhap_Id(Long idPhieuNhap);
    // List<CTPN> findByNgayNhapBetween(LocalDate fromDate, LocalDate toDate);

}