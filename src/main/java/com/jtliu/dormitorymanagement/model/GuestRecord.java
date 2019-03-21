package com.jtliu.dormitorymanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class GuestRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String phone;
    @ManyToOne
    User host;
    String reason;
    Date checkin;
    Date checkout;
}
