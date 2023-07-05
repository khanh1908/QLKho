package com.tttn.qlkho.Ropository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tttn.qlkho.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
