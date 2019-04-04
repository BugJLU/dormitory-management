package com.jtliu.dormitorymanagement.controller;

import com.jtliu.dormitorymanagement.controller.vo.StudentVo;
import com.jtliu.dormitorymanagement.controller.vo.UserVo;
import com.jtliu.dormitorymanagement.model.StudentInfo;
import com.jtliu.dormitorymanagement.model.User;
import com.jtliu.dormitorymanagement.service.StudentService;
import com.jtliu.dormitorymanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {

    private final UserService userService;
    private final StudentService studentService;

    @RequestMapping({"/", "/index"})
    public String index(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (user.getRole().equals(1)) {
                return "student/index";
            }
            if (user.getRole().equals(0)) {
                return "redirect:admin/index";
            }
        }
        return "student/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String jump = ControllerUtil.checkLoginState(request);
        if (jump != null) return jump;
        return "student/login";
    }

    @RequestMapping("/loginAct")
    public String loginAct(HttpServletRequest request, UserVo userVo) {
        String jump = ControllerUtil.checkLoginState(request);
        if (jump != null) return jump;
        User user = userService.studentLoginCheck(
                userVo.getPhone(),
                userVo.getPassword()
        );
        StudentInfo studentInfo = studentService.searchByUser(user);
        if (user == null || studentInfo == null) {
            request.setAttribute("action", "login");
            request.setAttribute("reason", "Username or password error.");
            return "fail";
        }
        request.removeAttribute("admin");
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("student", studentInfo);
//        if (studentInfo.getGender() == 0) System.out.println("000");
//        else System.out.println("111");
        return "redirect:/student/index";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request) {
        return "student/register";
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, StudentVo student) {
        request.setAttribute("action", "update student info");
        StudentInfo ss = (StudentInfo) request.getSession().getAttribute("student");
        if (ss == null) return "student/login";

        try {
            ss = studentService.updateInfo(student, ss.getId());
            if (ss == null) return "fail";
        } catch (Exception e) {
            request.setAttribute("reason", e.getMessage());
            return "fail";
        }

        request.getSession().setAttribute("user", ss.getBase());
        request.getSession().setAttribute("student", ss);
        return "success";
    }

    @RequestMapping("/registerAct")
    public String registerAct(HttpServletRequest request, StudentVo student) {
        String jump = ControllerUtil.checkLoginState(request);
        if (jump != null) return jump;

        request.setAttribute("action", "student register");

        try {
            StudentInfo studentInfo = studentService.saveInfo(student);
            if (studentInfo == null)
                throw new Exception("Cannot register user.");
        } catch (Exception e) {
            request.setAttribute("reason", e.getMessage());
            return "fail";
        }

        return "success";
    }
}
