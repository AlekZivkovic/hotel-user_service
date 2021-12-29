package raf.sk.user.service.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import raf.sk.user.service.user.service.domain.User;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends JpaRepository<T,Long> {
}
