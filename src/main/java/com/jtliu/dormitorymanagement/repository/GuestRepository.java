package com.jtliu.dormitorymanagement.repository;

import com.jtliu.dormitorymanagement.model.GuestRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<GuestRecord, Integer> {
    GuestRecord save(GuestRecord guestRecord);
    List<GuestRecord> findByPhone(String phone);
    List<GuestRecord> findByName(String name);
//    List<GuestRecord> findByPhone(String phone);
    List<GuestRecord> findByCheckoutIsNullAndPhone(String phone);
    List<GuestRecord> findByCheckinBetween(Date time1, Date time2);
//    GuestRecord findById(Integer id);
}
