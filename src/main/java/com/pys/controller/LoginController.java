package com.pys.controller;

import com.pys.entity.User;
import com.pys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

   @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletRequest request,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if(user==null){
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }else{
            user.setPassword(null);
            request.getSession().getServletContext().setAttribute("user",user);
            return "admin/index";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().getServletContext().removeAttribute("user");
        return "redirect:/admin";
    }
}
