package com.raf.sk.hoteluserservice.repository;

import com.raf.sk.hoteluserservice.domain.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRatingRepositroy extends JpaRepository<UserRating,Long> {
}
