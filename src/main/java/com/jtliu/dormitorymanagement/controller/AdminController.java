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
import java.util.ArrayList;
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
        if (jump != null) return jump;
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

//    @RequestMapping("/update")
//    public String update(HttpServletRequest request, UserVo admin) {
//        request.setAttribute("action", "update admin info");
//        User ad = (User)request.getSession().getAttribute("admin");
//        if (ad == null) return "/";
//        try {
//            User base = userService.searchById(admin.getId());
//            admin.setPassword(base.getPassword());
//            base = adminService.updateUserInfo(admin, base.getId());
//            if (base == null) return "fail";
//        } catch (Exception e) {
//            request.setAttribute("reason", e.getMessage());
//            return "fail";
//        }
////        request.getSession().setAttribute("admin", ad);
//        return "success";
//    }

    @RequestMapping("/registerAct")
    public String registerAct(HttpServletRequest request, UserVo admin) {
//        String jump = ControllerUtil.checkLoginState(request);
//        if (jump != null) return jump;

        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getRole() != 0) {
            return "redirect:/";
        }

        request.setAttribute("action", "admin add");

        try {
            User adminInfo = adminService.saveUserInfo(admin);
            if (adminInfo == null)
                throw new Exception("Cannot add user.");
        } catch (Exception e) {
            request.setAttribute("reason", e.getMessage());
            return "fail";
        }

        return "redirect:/admin/adminInfo";
    }

    @RequestMapping("/adminInfo")
    public String adminInfo(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        String s = ControllerUtil.checkLoginState(request);
        if (ad == null) {
            if(s == null)
                return "redirect:/";
            else
                return s;
        }
        List<User> users = adminService.searchByRole(0);
        request.setAttribute("users", users);
        return "admin/adminInfo";
    }

    @RequestMapping("/adminAct")
    public String adminAct(HttpServletRequest request, UserVo admin){
        request.setAttribute("action", "update admin info");
        User ad = (User)request.getSession().getAttribute("admin");
        if (ad == null) return "redirect:/";
        try {
            User base = userService.searchById(admin.getId());
            admin.setPassword(base.getPassword());
            base = adminService.updateUserInfo(admin, base.getId());
            if (base == null) return "fail";
        } catch (Exception e) {
            request.setAttribute("reason", e.getMessage());
            return "fail";
        }
        return "redirect:/admin/adminInfo";
    }

    @RequestMapping("/removeAdmin")
    public String removeAdmin(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        if (ad == null) return "redirect:/";

        Integer aid = Integer.parseInt(request.getParameter("aid"));
        if(!adminService.removeAdmin(aid)) return "fail";
        return "redirect:/admin/adminInfo";
    }

    @RequestMapping("/checkInfo")
    public String checkInfo(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        String s = ControllerUtil.checkLoginState(request);
        if (ad == null) {
            if(s == null)
                return "redirect:/";
            else
                return s;
        }
        List<GuestRecord> guestRecords = adminService.searchAllGuestRecord();
        request.setAttribute("records",guestRecords);
        return "admin/checkInfo";
    }

    @RequestMapping("/roomInfo")
    public String roomInfo(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        String s = ControllerUtil.checkLoginState(request);
        if (ad == null) {
            if(s == null)
                return "redirect:/";
            else
                return s;
        }
        Map<String,List<StudentInfo>> roomInfo = adminService.searchRoomInfo();
        List<Room> roomList = adminService.searchAllRoom();
//        if(roomList == null) return "";
        request.setAttribute("roomList",roomList);
        request.setAttribute("roomInfo",roomInfo);
        return "admin/roomInfo";
    }

    @RequestMapping("/addRoom")
    public String addRoom(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        String s = ControllerUtil.checkLoginState(request);
        if (ad == null) {
            if(s == null)
                return "redirect:/";
            else
                return s;
        }
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
        return "redirect:/admin/roomInfo";
    }

    @RequestMapping("/updateRoom")
    public String updateRoom(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        String s = ControllerUtil.checkLoginState(request);
        if (ad == null) {
            if(s == null)
            return "redirect:/";
        else
            return s;
        }
        String roomNum = request.getParameter("roomNum");
        request.setAttribute("roomNum",roomNum);
        List<StudentInfo> studentList = adminService.searchStudentByRoom(roomNum);
        List<StudentInfo> studentNoRoomTemp = adminService.searchStudentNoRoom();
        List<StudentInfo> studentNoRoom = adminService.searchStudentNoRoom();
        if(studentList.size() != 0)  {
            for(StudentInfo student : studentNoRoomTemp){
                if(student.getGender() != adminService.searchGenderOfRoom(roomNum)) studentNoRoom.remove(student);
            }
        }
        Integer i = adminService.searchGenderOfRoom(roomNum);
        String genderString = "";
        if(i == 0) genderString = "Male";
        if(i == 1) genderString = "Female";
        request.setAttribute("genderString",genderString);
        request.setAttribute("studentList",studentList);
        request.setAttribute("studentNoRoom",studentNoRoom);
        return "admin/updateRoom";
    }


    //Add Student To Room
    @RequestMapping("/addRoomToStudent")
    public String updateRoomAct(HttpServletRequest request){
        String roomNum = (String)request.getParameter("roomNum");
        Integer sid = Integer.parseInt(request.getParameter("sid"));
        if(!adminService.addRoomToStudent(sid,roomNum)) return "fail";
        return "redirect:/admin/updateRoom?roomNum="+roomNum;
    }

    @RequestMapping("/studentInfo")
    public String studentInfo(HttpServletRequest request){
        User ad = (User)request.getSession().getAttribute("admin");
        String s = ControllerUtil.checkLoginState(request);
        if (ad == null) {
            if(s == null)
                return "redirect:/";
            else
                return s;
        }
        List<StudentInfo> students = adminService.searchAllStudent();
        request.setAttribute("users",students);
        //
        request.setAttribute("genderRoomMap",adminService.searchGenderRoomMap());
        //
        return "admin/studentInfo";
    }

    @RequestMapping("/studentAct")
    public String studentAct(HttpServletRequest request,StudentVo studentVo){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getRole() != 0) {
            return "redirect:/";
        }
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
        return "redirect:/admin/studentInfo";
    }

    @RequestMapping("/removeStudent")
    public String removeStudent(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getRole() != 0) {
            return "redirect:/";
        }
        Integer sid = Integer.parseInt(request.getParameter("sid"));
        if(!adminService.removeStudent(sid)) return "fail";
        return "redirect:/admin/studentInfo";
    }

    @RequestMapping("/removeRoomFromStudent")
    public String removeRoomFromStudent(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getRole() != 0) {
            return "redirect:/";
        }
        Integer sid = Integer.parseInt(request.getParameter("sid"));
        String rnum = studentService.searchById(sid).getRoom().getRoomNum();
        if(!adminService.removeRoomFromStudent(sid)) return "fail";
        return "redirect:/admin/updateRoom?roomNum="+rnum;
    }
}
