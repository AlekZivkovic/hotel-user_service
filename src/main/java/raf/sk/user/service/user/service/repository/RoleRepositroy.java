package raf.sk.user.service.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.sk.user.service.user.service.domain.Role;

public interface RoleRepositroy extends JpaRepository<Role,Long> {
}
