package com.yuanhao.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Yuanhao
 */
@Controller
public class FormController {

    @GetMapping("/formLayouts")
    public String formLayouts() {
        return "form/form_layouts";
    }

    @PostMapping("/upload1")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestPart("file") MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            file.transferTo(new File("C:\\Users\\DELL\\Desktop\\新建文件夹\\" + originalFilename));
        }
        return "index";
    }

    @PostMapping("/upload2")
    public String upload(@RequestParam("qq") String qq,
                         @RequestParam("weChat") String weChat,
                         @RequestPart("files") MultipartFile[] files) throws IOException {
        if(files.length > 0) {
            for(MultipartFile file : files) {
                if(!file.isEmpty()) {
                    String originalFilename = file.getOriginalFilename();
                    file.transferTo(new File("C:\\Users\\DELL\\Desktop\\新建文件夹\\" + originalFilename));
                }
            }
        }
        return "index";
    }
}
