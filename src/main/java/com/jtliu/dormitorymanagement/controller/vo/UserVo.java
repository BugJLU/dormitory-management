package com.jtliu.dormitorymanagement.controller.vo;

import com.jtliu.dormitorymanagement.model.User;
import lombok.Data;

@Data
public class UserVo {
    Integer id;
    String name;
    String phone;
    String password;

    public UserVo() {}
    public UserVo(User user) {
        name = user.getName();
        phone = user.getPhone();
    }
}
