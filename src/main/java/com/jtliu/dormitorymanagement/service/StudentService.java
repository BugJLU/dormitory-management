package com.jtliu.dormitorymanagement.service;

import com.jtliu.dormitorymanagement.controller.vo.StudentVo;
import com.jtliu.dormitorymanagement.model.StudentInfo;
import com.jtliu.dormitorymanagement.model.User;
import com.jtliu.dormitorymanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserService userService;

    public List<StudentInfo> searchByChoice(Integer choice, String value) {
        value = "%"+value+"%";
        switch (choice) {
            case 0:
                return studentRepository.findByBase_NameLike(value);
            case 1:
                return studentRepository.findByBase_PhoneLike(value);
            case 2:
                return studentRepository.findByStudentIdLike(value);
            default:
                return null;
        }
    }

    public StudentInfo searchByUser(User user) {
        if (user == null || user.getId() == null) return null;
        return studentRepository.findByBase_Id(user.getId());
    }

    public StudentInfo searchById(Integer id) {
        if (id == null) return null;
        return studentRepository.findById(id).orElse(null);
    }

    public StudentInfo saveInfo(StudentVo student) {
        if (student == null) return null;
        StudentInfo studentInfo = new StudentInfo();
        User user = new User();
        user.setName(student.getName());
        user.setPhone(student.getPhone());
        user.setPassword(student.getPassword());
        user.setRole(1);
        user = userService.saveUser(user);
        studentInfo.setBase(user);
        studentInfo.setStudentId(student.getStudentId());
        studentInfo.setGender(student.getGender());
        return studentRepository.save(studentInfo);
    }

    public StudentInfo updateInfo(StudentVo student, Integer sid) {
        StudentInfo studentInfo = studentRepository.findById(sid).orElse(null);
        if (studentInfo == null) return null;
        User user = studentInfo.getBase();
        user.setName(student.getName());
        user.setPhone(student.getPhone());
//        user = userService.saveUser(user);
        studentInfo.setBase(user);
        studentInfo.setStudentId(student.getStudentId());
//        studentInfo
        return studentRepository.save((studentInfo));
    }
}
