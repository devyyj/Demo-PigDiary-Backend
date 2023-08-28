package com.devyyj.pigdiary.user.repository;

import com.devyyj.pigdiary.user.entity.MyUser2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser2, Long> {
    Optional<MyUser2> findBySocialId(String socialId);
}
