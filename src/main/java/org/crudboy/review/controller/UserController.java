package org.crudboy.review.controller;

import org.crudboy.review.Entity.User;
import org.crudboy.review.Entity.UserReadState;
import org.crudboy.review.mapper.UserMapper;
import org.crudboy.review.service.UserService;
import org.crudboy.review.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/registry")
    public ModelAndView showRegistry() {
        return new ModelAndView("registry");
    }

    @PostMapping("/register")
    @ResponseBody
    public Map register(String verifyCode, String username, String password,
                        String nickname, HttpServletRequest request) {

        Map result = new HashMap();

        String checkCode = (String)request.getSession().getAttribute("verifyCode");
        if (verifyCode == null || !verifyCode.equalsIgnoreCase(checkCode)) {
            result.put("code", "VC01");
            result.put("msg", "验证码错误");
        } else {
            result.put("code", "0");
            result.put("msg", "success");
        }

        try {
            userService.createUser(username, password, nickname);
        } catch (BussinessException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }

        return  result;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/check_login")
    @ResponseBody
    public Map chekLogin(String username, String password, String verifyCode,
                         HttpSession session) {

        Map result = new HashMap();

        String checkCode = (String)session.getAttribute("verifyCode");
        if (verifyCode == null || !verifyCode.equalsIgnoreCase(checkCode)) {
            result.put("code", "VC01");
            result.put("msg", "验证码错误");
        } else {
            try {
                User user = userService.checkLogin(username, password);
                session.setAttribute("loginUser", user);
                result.put("code", "0");
                result.put("msg", "success");
            } catch(BussinessException e) {
                e.printStackTrace();
                result.put("code", e.getCode());
                result.put("msg", e.getMsg());
            }
        }
        return result;
    }
}
