package com.jtliu.dormitorymanagement.controller;

import com.jtliu.dormitorymanagement.controller.vo.StudentVo;
import com.jtliu.dormitorymanagement.controller.vo.UserVo;
import com.jtliu.dormitorymanagement.model.GuestRecord;
import com.jtliu.dormitorymanagement.model.Room;
import com.jtliu.dormitorymanagement.model.StudentInfo;
import com.jtliu.dormitorymanagement.model.User;
import com.jtliu.dormitorymanagement.service.AdminService;
import com.jtliu.dormitorymanagement.service.StudentService;
import com.jtliu.dormitorymanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;
    private final StudentService studentService;

    @RequestMapping({"/", "/index"})
    public String index(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && user.getRole().equals(0)) {
            return "admin/index";
        }
        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String jump = ControllerUtil.checkLoginState(request);
        if (jump != null) return jump;
        return "admin/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        request.getSession().removeAttribute("user");
        return "index";
    }

    @RequestMapping("/loginAct")
    public String loginAct(HttpServletRequest request, UserVo userVo) {
        String jump = ControllerUtil.checkLoginState(request);
        if (jump != "redirect:/") return jump;
        User user = userService.adminLoginCheck(
                userVo.getPhone(),
                userVo.getPassword()
        );
        //user or  new admin
        User adminInfo = adminService.searchByUser(user);
        if (user == null || adminInfo == null) {
            request.setAttribute("action", "login");
            request.setAttribute("reason", "Username or password error.");
            return "fail";
        }
        //request.getSession().removeAttribute("student");
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("admin", adminInfo);
        return "redirect:/admin/index";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request) {
        return "admin/register";
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, UserVo admin) {
        request.setAttribute("action", "update admin info");
        User ad = (User)request.getSession().getAttribute("admin");
        if (ad == null) return "admin/login";
        ad = adminService.updateUserInfo(admin, ad.getPhone());
        if (ad == null) return "fail";
        request.getSession().setAttribute("admin", ad);
        return "success";
    }

    @RequestMapping("/registerAct")
    public String registerAct(HttpServletRequest request, UserVo admin) {
        String jump = ControllerUtil.checkLoginState(request);
        if (jump != null) return jump;

        request.setAttribute("action", "admin register");

        try {
            User adminInfo = adminService.saveUserInfo(admin);
            if (adminInfo == null)
                throw new Exception("Cannot register user.");
        } catch (Exception e) {
            request.setAttribute("reason", e.getMessage());
            return "fail";
        }

        return "success";
    }

    @RequestMapping("/adminInfo")
    public String adminInfo(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        if (ad == null) return ControllerUtil.checkLoginState(request);
        List<User> users = adminService.searchByRole(0);
        request.setAttribute("users", users);
        return "admin/adminInfo";
    }

    @RequestMapping("/adminAct")
    public String adminAct(HttpServletRequest request){
        request.setAttribute("action", "update admin info");
        User ad = adminService.searchByPhone(request.getParameter("phone"));
        if (ad == null) return "fail";
        UserVo admin = new UserVo();
        admin.setName(request.getParameter("name"));
        admin.setPhone(request.getParameter("phone"));
        admin.setPassword(request.getParameter("password"));
        ad = adminService.updateUserInfo(admin, ad.getPhone());
        if (ad == null) return "fail";
        return "success";
    }

    @RequestMapping("/checkInfo")
    public String checkInfo(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        if (ad == null) return ControllerUtil.checkLoginState(request);
        List<GuestRecord> guestRecords = adminService.searchAllGuestRecord();
        request.setAttribute("records",guestRecords);
        return "admin/checkInfo";
    }

    @RequestMapping("/roomInfo")
    public String roomInfo(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        if (ad == null) return ControllerUtil.checkLoginState(request);
        Map<String,List<StudentInfo>> rooms = adminService.searchRoomInfo();
        List<Room> roomList = adminService.searchAllRoom();
        if(roomList == null) return null;
        request.setAttribute("roomList",roomList);
        request.setAttribute("rooms",rooms);
        return "admin/roomInfo";
    }

    @RequestMapping("/addRoom")
    public String addRoom(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        if (ad == null) return ControllerUtil.checkLoginState(request);
        return "admin/addRoom";
    }

    @RequestMapping("/addRoomAct")
    public String addRoomAct(HttpServletRequest request){
        String roomNum = (String)request.getParameter("roomNum");
        Room room = new Room();
        try{
        room = adminService.saveRoom(roomNum);
        }catch(RuntimeException r){
            request.setAttribute("reason","Duplicate RoomNum");
            request.setAttribute("action","Add Room");
            return "fail";
        }
        return "success";
    }

    @RequestMapping("/updateRoom")
    public String updateRoom(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        if (ad == null) return ControllerUtil.checkLoginState(request);
        String roomNum = request.getParameter("roomNum");
        request.setAttribute("roomNum",roomNum);
        List<StudentInfo> studentList = adminService.searchStudentByRoom(roomNum);
        request.setAttribute("studentList",studentList);
        return "admin/updateRoom";
    }

    @RequestMapping("/updateRoomAct")
    public String updateRoomAct(HttpServletRequest request){
        String roomNum = (String)request.getParameter("roomNum");
        List<StudentInfo> studentList = adminService.searchStudentByRoom(roomNum);
        return "success";
    }

    @RequestMapping("/studentInfo")
    public String studentInfo(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        if (ad == null) return ControllerUtil.checkLoginState(request);
        List<StudentInfo> students = adminService.searchAllStudent();
        request.setAttribute("users",students);
        //
        request.setAttribute("rooms",adminService.searchAllRoom());
        //
        return "admin/studentInfo";
    }

    @RequestMapping("/studentAct")
    public String studentAct(HttpServletRequest request,StudentVo studentVo){
        request.setAttribute("action", "update student info");
        StudentInfo student = studentService.searchById(studentVo.getId());
        User  base = adminService.searchById(student.getBase().getId());
        if (base == null) return "fail";
        base.setPhone(studentVo.getPhone());
        base.setName(studentVo.getName());
        if (student == null) return "fail";
        student.setBase(base);
        student.setStudentId(studentVo.getStudentId());
        student.setRoom(adminService.searchRoom(studentVo.getRoom()));
        student = adminService.updateStudentInfo(student);
        if (student == null) return "fail";
        return "success";
    }
}
