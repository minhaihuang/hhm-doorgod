package com.hhm.doorgod.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author huanghm
 * @Date 2023/3/6
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hello")
    public String satHello(@RequestParam String name){
        return "hello," + name;
    }

    @GetMapping("/hello2")
    public String satHello2(@RequestParam String name){
        return "hello," + name;
    }
}
