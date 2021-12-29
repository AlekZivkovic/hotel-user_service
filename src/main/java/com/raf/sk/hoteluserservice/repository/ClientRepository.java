package com.raf.sk.hoteluserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.raf.sk.hoteluserservice.domain.Client;

public interface ClientRepository  extends UserBaseRepository<Client>, JpaRepository<Client,Long> {
}
