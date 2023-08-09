package com.devyyj.pigdiary.user.repository;

import com.devyyj.pigdiary.user.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findBySocialId(String socialId);
}
