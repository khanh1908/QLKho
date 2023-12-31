package com.tttn.qlkho.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByUserName(String userName);
    User findByEmail(String email);
    User findByCCCD(String cccd);
    User findBySDT(String sdt);
}
