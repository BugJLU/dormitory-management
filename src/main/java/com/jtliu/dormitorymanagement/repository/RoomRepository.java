package com.jtliu.dormitorymanagement.repository;

import com.jtliu.dormitorymanagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findByRoomNum(String num);
    List<Room> findAll();
}
