package com.jxxdxy.controller;

import com.jxxdxy.entity.User;
import com.jxxdxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpSession session){
        if (username == null || "".equals(username) || password == null || "".equals(password)){
            model.addAttribute("msg", "用户名和密码不能为空");
            return "/user/loginView";
        }
        if (!userService.queryUserByUsernameAndPassword(username, password)){
            model.addAttribute("msg", "用户名或密码错误！");
            return "/user/loginView";
        }
        model.addAttribute("msg", username);
        session.setAttribute("user", userService.queryUserByUsername(username));
        return "/user/admin/index";
    }

    @RequestMapping("/register")
    public String register(User user, Model model, HttpSession httpSession){
        String username = user.getUsername();
        String password = user.getPassword();
        if (username == null || "".equals(username) || password == null || "".equals(password)){
            model.addAttribute("msg", "注册失败，用户名和密码不能为空");
            return "/user/registerView";
        }
        User user1 = userService.queryUserByUsername(username);
        if (user1 != null){
            model.addAttribute("msg", "该用户已存在");
            return "/user/registerView";
        }
        boolean b = userService.userRegister(user);
        if (b) {
            model.addAttribute("msg", "注册成功，将自动登录");
            httpSession.setAttribute("user", userService.queryUserByUsername(username));
            return "/user/admin/index";
        }else {
            model.addAttribute("msg", "注册失败，未知原因");
            return "/user/registerView";
        }
    }

    @RequestMapping("/loginView")
    public ModelAndView loginView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login.jsp");
        return modelAndView;
    }
    @RequestMapping("/registerView")
    public ModelAndView registerView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/register.jsp");
        return modelAndView;
    }

    @RequestMapping("/admin/index")
    public ModelAndView adminIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/admin/index.jsp");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/logout")
    public String logout(HttpSession httpSession){
        if (httpSession.getAttribute("user") != null)
            httpSession.invalidate();
        System.out.println("logout");
        return "logout";

    }

}
