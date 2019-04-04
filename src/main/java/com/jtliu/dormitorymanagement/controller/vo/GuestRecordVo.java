package com.jtliu.dormitorymanagement.controller.vo;

import com.jtliu.dormitorymanagement.model.GuestRecord;
import com.jtliu.dormitorymanagement.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class GuestRecordVo {
//    Integer id;
    String name;
    String phone;
    Integer host;
    String reason;
//    Date checkin;
//    Date checkout;

//    public GuestRecordVo() { }
//    public GuestRecordVo(GuestRecord guestRecord) {
//        name = guestRecord.getName();
//        phone = guestRecord.getPhone();
//        host = new UserVo(guestRecord.getHost());
//        reason = guestRecord.getReason();
////        checkin = guestRecord.getCheckin();
////        checkout = guestRecord.getCheckout();
////        id = guestRecord.getId();
//    }

}
