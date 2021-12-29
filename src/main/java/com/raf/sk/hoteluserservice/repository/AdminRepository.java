package com.raf.sk.hoteluserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.raf.sk.hoteluserservice.domain.Admin;

public interface AdminRepository extends UserBaseRepository<Admin>, JpaRepository<Admin,Long> {
}
