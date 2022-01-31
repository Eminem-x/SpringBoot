package com.yuanhao.boot.controller;

import com.yuanhao.boot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yuanhao
 */
@RestController
public class HelloController {
    @Autowired
    Person person;

    @RequestMapping("/person")
    public Person person() {
        return person;
    }
}
