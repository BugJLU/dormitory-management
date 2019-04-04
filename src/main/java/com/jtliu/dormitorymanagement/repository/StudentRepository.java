package com.jtliu.dormitorymanagement.repository;

import com.jtliu.dormitorymanagement.model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentInfo, Integer> {
    StudentInfo findByBase_Id(Integer baseid);

    List<StudentInfo> findAll();

    List<StudentInfo> findByStudentIdLike(String sid);

    List<StudentInfo> findByBase_NameLike(String name);

    List<StudentInfo> findByBase_PhoneLike(String phone);
}
