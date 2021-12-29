package com.raf.sk.hoteluserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.raf.sk.hoteluserservice.domain.User;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends JpaRepository<T,Long> {
}
