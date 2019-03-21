package com.jtliu.dormitorymanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @Column(unique = true, nullable = false)
    String phone;
    String password;

    /**
     * @Description:
     * 0    dormitory admin
     * 1    student
     * 2    ...
     */
    Integer role;
}
