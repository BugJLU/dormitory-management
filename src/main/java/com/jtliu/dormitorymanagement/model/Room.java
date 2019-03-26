package com.jtliu.dormitorymanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(unique = true, nullable = false)
    String roomNum;
}
