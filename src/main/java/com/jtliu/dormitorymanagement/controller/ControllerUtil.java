package com.jtliu.dormitorymanagement.controller;

import com.jtliu.dormitorymanagement.model.User;

import javax.servlet.http.HttpServletRequest;

public class ControllerUtil {
    static String checkLoginState(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            switch (user.getRole()) {
                case 0:
                    return "redirect:/admin/index";
                case 1:
                    return "redirect:/student/index";
                default:
//                    return "redirect:/";
                    return null;
            }
        }
//        return "redirect:/";
        return null;
    }
}
