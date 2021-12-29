package com.raf.sk.hoteluserservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.raf.sk.hoteluserservice.domain.Manager;

public interface ManagerRepository extends UserBaseRepository<Manager>, JpaRepository<Manager,Long> {
}
