package com.devyyj.pigdiary.user.repository;

import com.devyyj.pigdiary.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
