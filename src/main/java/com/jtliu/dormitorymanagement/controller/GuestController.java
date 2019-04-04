package com.jtliu.dormitorymanagement.controller;

import com.jtliu.dormitorymanagement.controller.vo.GuestRecordVo;
import com.jtliu.dormitorymanagement.model.GuestRecord;
import com.jtliu.dormitorymanagement.model.User;
import com.jtliu.dormitorymanagement.service.GuestService;
import com.jtliu.dormitorymanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/guest")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GuestController {
    private final GuestService guestService;
    private final UserService userService;

    @RequestMapping({"/", "/index"})
    public String index() {
        return "guest/index";
    }

    @RequestMapping("/checkin")
    public String checkin(HttpServletRequest request) {
        return "guest/searchnchoose";
    }

    @RequestMapping("/checkinPage")
    public String checkinPage(HttpServletRequest request) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        User host = userService.searchById(id);
        request.setAttribute("host", host);
        return "guest/checkin";
    }

    @RequestMapping("/checkinAct")
    public String checkinAct(GuestRecordVo guest) {
//        request.setAttribute("action", "checkin");
//        GuestRecordVo guest = (GuestRecordVo) request.getAttribute("guest");
        if (guestService.checkin(guest) != null) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping("/searchNchoose")
    public String searchNchoose(HttpServletRequest request) {
        String name = (String) request.getParameter("name");
        List<User> users = userService.searchByName(name);
        request.setAttribute("users", users);
        return "guest/searchnchoose";
    }

    @RequestMapping("/checkout")
    public String checkout(HttpServletRequest request, String phone) {
        List<GuestRecord> guestRecord = guestService.searchCheckoutByPhone(phone);
        request.setAttribute("records", guestRecord);
        return "guest/checkout";
    }

    @RequestMapping("/checkoutAct")
    public String checkoutAct(HttpServletRequest request, Integer id) {
        request.setAttribute("action", "checkout");
        GuestRecord guestRecord = guestService.checkout(id);
        if (guestRecord != null) {
            return "success";
        } else {
            return "fail";
        }
    }
}
