package com.raf.sk.hoteluserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.raf.sk.hoteluserservice.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends  JpaRepository<User,Long> {
    Optional<User> findUserByUsernameAndPassword(String username, String password);
    //bice potrebo da se ugrabi svi menadzeri datog hotela
    Optional<List<User>> findAllUserByManagersInfoHotelName(String hotelName);

}
