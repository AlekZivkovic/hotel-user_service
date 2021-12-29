package raf.sk.user.service.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.sk.user.service.user.service.domain.Admin;

public interface AdminRepository extends UserBaseRepository<Admin>, JpaRepository<Admin,Long> {
}
