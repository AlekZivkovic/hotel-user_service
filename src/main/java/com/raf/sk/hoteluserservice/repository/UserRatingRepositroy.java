package com.raf.sk.hoteluserservice.repository;

import com.raf.sk.hoteluserservice.domain.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRatingRepositroy extends JpaRepository<UserRating,Long> {
}
