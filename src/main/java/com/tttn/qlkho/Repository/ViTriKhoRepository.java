package com.tttn.qlkho.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.Vitrikho;

@Repository
public interface ViTriKhoRepository extends JpaRepository<Vitrikho, Long>{
    
}
