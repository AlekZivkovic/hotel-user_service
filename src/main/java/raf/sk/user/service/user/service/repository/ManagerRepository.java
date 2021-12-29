package raf.sk.user.service.user.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import raf.sk.user.service.user.service.domain.Manager;

public interface ManagerRepository extends UserBaseRepository<Manager>, JpaRepository<Manager,Long> {
}
