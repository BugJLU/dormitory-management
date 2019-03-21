package com.jtliu.dormitorymanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class StudentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @OneToOne
    User base;
    String studentId;
    @ManyToOne
    Room room;
}
