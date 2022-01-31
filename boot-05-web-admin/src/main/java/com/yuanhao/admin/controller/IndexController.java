package com.yuanhao.admin.controller;

import com.yuanhao.admin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Yuanhao
 */
@Controller
public class IndexController {

    @GetMapping(value = {"/","/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model) {
        if(!StringUtils.isEmpty(user.getUsername()) && StringUtils.hasLength(user.getPassword())) {
            // 保存登录成功的用户
            session.setAttribute("loginUser", user);
            // 重定向 防止表单提交
            return "redirect:/index.html";
        }
        model.addAttribute("msg", "账号或密码错误");
        // 返回到登陆页面
        return "login";
    }

    @GetMapping("/index.html")
    public String mainPage(HttpSession session, Model model) {
        // 判断是否登录
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser != null) {
            return "index";
        }
        model.addAttribute("msg", "请重新登录");
        return "login";
    }
}
