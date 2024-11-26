package com.cqjtu.javawork3.repository;

import com.cqjtu.javawork3.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "select u from User u where u.username = :username")
    User findByUsername(Long username);

    @Query(value = "select u from User u where u.role like :findCondition or u.username = :findCondition")
    List<User> findByFindCondition(String findCondition);
}
