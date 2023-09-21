package com.tttn.qlkho.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.CTPX;

@Repository
public interface CTPXRepository extends JpaRepository<CTPX,Long>{
        // List<CTPX> findByPhieuXuat_Id(Long idphieuxuat);
        List<CTPX> findByPhieuxuat_Id(Long idphieuxuat);
        // List<CTPX> findByNgayXuatBetween(LocalDate fromDate, LocalDate toDate);

    }
