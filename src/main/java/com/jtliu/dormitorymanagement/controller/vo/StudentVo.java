package com.jtliu.dormitorymanagement.controller.vo;

import lombok.Data;

@Data
public class StudentVo {
    Integer id;
    String name;
    String phone;
    Integer gender;
    String password;
    String studentId;
    String room;
}
