package com.raf.sk.hoteluserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.raf.sk.hoteluserservice.domain.Role;

public interface RoleRepositroy extends JpaRepository<Role,Long> {
}
