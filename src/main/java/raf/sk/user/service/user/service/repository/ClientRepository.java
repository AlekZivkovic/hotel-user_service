package raf.sk.user.service.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raf.sk.user.service.user.service.domain.Client;

public interface ClientRepository  extends UserBaseRepository<Client>, JpaRepository<Client,Long> {
}
