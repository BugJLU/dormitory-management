package com.jtliu.dormitorymanagement.service;

import com.jtliu.dormitorymanagement.controller.vo.StudentVo;
import com.jtliu.dormitorymanagement.controller.vo.UserVo;
import com.jtliu.dormitorymanagement.model.GuestRecord;
import com.jtliu.dormitorymanagement.model.Room;
import com.jtliu.dormitorymanagement.model.StudentInfo;
import com.jtliu.dormitorymanagement.model.User;
import com.jtliu.dormitorymanagement.repository.RoomRepository;
import com.jtliu.dormitorymanagement.repository.StudentRepository;
import com.jtliu.dormitorymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final GuestService guestService;
    private final StudentService studentService;
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;

    public User searchByUser(User user) {
        if (user == null || user.getId() == null) return null;
        return userRepository.findByPhone(user.getPhone());
    }

    public User searchByPhone(String phone) {
        if (phone == null) return null;
        return userRepository.findByPhone(phone);
    }

    public User searchById(Integer id) {
        if (id == null) return null;
        return userRepository.findById(id).orElse(null);
    }

    public List<User> searchByRole(Integer r) {
        if (r == null) return null;
        return userRepository.findAllByRole(r);
    }

    public User updateUserInfo(UserVo uservo,String phone) {
        User user  = userRepository.findByPhone(phone);
        if(user == null) return null;
        user.setName(uservo.getName());
        user.setPhone(uservo.getPhone());
        user.setPassword(uservo.getPassword());
        return userRepository.save(user);
    }

    public User saveUserInfo(UserVo uservo) {
        if(uservo == null) return  null;
        User user = new User();
        user.setName(uservo.getName());
        user.setPhone(uservo.getPhone());
        user.setPassword(uservo.getPassword());
        user.setRole(0);
//        user = userService.saveUser(user);
//        return userRepository.save(user);
        return userService.saveUser(user);
    }

    public List<GuestRecord> searchAllGuestRecord(){
        return guestService.searchAll();
    }

    public List<StudentInfo> searchAllStudent(){
        List<StudentInfo> students= studentRepository.findAll();
        return students;
    }


    public StudentInfo updateStudentInfo(StudentInfo student){
        User user = student.getBase();
        try{
        user = userService.saveUser(user);
        }catch(RuntimeException r){
            return null;
        }
        StudentInfo ss = studentRepository.findByBase_Id(student.getBase().getId());
        ss.setStudentId(student.getStudentId());
        ss.setRoom(student.getRoom());
        ss = studentRepository.save(ss);
        return ss;
    }

    public Room searchRoom(String num){
        if(num == "NoRoom") return null;
        return roomRepository.findByRoomNum(num);
    }

    public Map<String,List<StudentInfo>> searchRoomInfo(){
        List<Room> roomList = roomRepository.findAll();
        if(roomList == null) return null;
        List<StudentInfo> studentInfos = studentRepository.findAll();
        Map<String,List<StudentInfo>> roomMap = new HashMap<>();
        for(Room r : roomList){
            roomMap.put(r.getRoomNum(),new ArrayList<>());
        }
        for(StudentInfo ss : studentInfos){
            roomMap.get(ss.getRoom().getRoomNum()).add(ss);
        }
        return roomMap;
    }

    public List<Room> searchAllRoom(){
        return roomRepository.findAll();
    }

    public Room saveRoom(String roomNum){
        if (roomNum == null) return null;
        Room room = new Room();
        room.setRoomNum(roomNum);
        room = roomRepository.save(room);
        return room;
    }

    public List<StudentInfo> searchStudentByRoom(String roomNum){
        if (roomNum == null) return null;
        Map<String,List<StudentInfo>> roomMap = searchRoomInfo();
        List<StudentInfo> studentList = roomMap.get(roomNum);
        return studentList;
    }
}
