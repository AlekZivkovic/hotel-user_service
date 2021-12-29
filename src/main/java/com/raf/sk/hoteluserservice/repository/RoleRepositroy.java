package com.raf.sk.hoteluserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.raf.sk.hoteluserservice.domain.Role;

import java.util.Optional;

public interface RoleRepositroy extends JpaRepository<Role,Long> {
    Optional<Role> findRoleByName(String name);
}
