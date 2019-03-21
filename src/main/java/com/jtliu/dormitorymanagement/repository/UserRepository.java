package com.jtliu.dormitorymanagement.repository;

import com.jtliu.dormitorymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByName(String name);
    User findByPhone(String phone);
    User findByPhoneAndPassword(String phone, String password);
}
