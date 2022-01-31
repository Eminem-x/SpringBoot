package com.yuanhao.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yuanhao
 */
@Controller
public class ViewTestController {

    @GetMapping("/yuanhao")
    public String yuanhao(Model model) {
        model.addAttribute("msg", "bilibili");
        model.addAttribute("link", "http://www.bilibili.com");
        return "success";
    }
}
