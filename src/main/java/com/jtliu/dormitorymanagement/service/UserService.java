package com.jtliu.dormitorymanagement.service;

import com.jtliu.dormitorymanagement.model.User;
import com.jtliu.dormitorymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserRepository userRepository;

    public List<User> searchByName(String name) {
        if (name == null) return null;
        return userRepository.findAllByName(name);
    }

    public User searchById(Integer id) {
        Optional<User> ouser = userRepository.findById(id);
        return ouser.orElse(null);
    }

    public User studentLoginCheck(String phone, String password) {
        if (phone == null || password == null) {
            return null;
        }
        User res = userRepository.findByPhoneAndPassword(phone, password);
        if (res != null && res.getRole() == 1) return res;
        return null;
    }

    public User adminLoginCheck(String phone, String password) {
        if (phone == null || password == null) {
            return null;
        }
        User res = userRepository.findByPhoneAndPassword(phone, password);
        if (res == null) return null;
        if (res.getRole() == 0) return res;
        return null;
    }

    public User saveUser(User user) {
        return userRepository.saveAndFlush(user);
    }
}
