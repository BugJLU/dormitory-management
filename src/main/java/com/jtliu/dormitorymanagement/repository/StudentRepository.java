package com.jtliu.dormitorymanagement.repository;

import com.jtliu.dormitorymanagement.model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentInfo, Integer> {
    StudentInfo findByBase_Id(Integer baseid);
}
