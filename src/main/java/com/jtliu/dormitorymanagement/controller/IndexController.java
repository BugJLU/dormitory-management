package com.jtliu.dormitorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        String jump = ControllerUtil.checkLoginState(request);
        if (jump != null) return jump;
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("student");
        request.getSession().removeAttribute("admin");
        return "index";
    }
}
