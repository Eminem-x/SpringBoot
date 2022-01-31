package com.yuanhao.boot.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yuanhao
 */
@RestController
public class ParameterTestController {

    @RequestMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String, String> header,
                                      @RequestParam("age") Integer age,
                                      @RequestParam("inters") List<String> inters,
                                      @RequestParam Map<String, String> params,
                                      @CookieValue("Webstorm-3da1c711") String _ga) {
        Map<String, Object> map = new HashMap<>();
//        map.put("id", id);
//        map.put("name", name);
//        map.put("pv", pv);
//
//        map.put("userAgent", userAgent);
//        map.put("header", header);

        map.put("age", age);
        map.put("inters", inters);
        map.put("params", params);
        map.put("_ga", _ga);
        return map;
    }

    @RequestMapping("/save")
    public Map<String, Object> postMethod(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }
}
