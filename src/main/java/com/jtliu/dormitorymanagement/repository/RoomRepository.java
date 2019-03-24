package com.jtliu.dormitorymanagement.repository;

import com.jtliu.dormitorymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByName(String name);
    User findByPhone(String phone);
    User findByPhoneAndPassword(String phone, String password);
    List<User> findAllByRole(Integer r);

}
