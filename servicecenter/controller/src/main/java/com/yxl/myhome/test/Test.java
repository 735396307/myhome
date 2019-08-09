package com.yxl.myhome.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/test")
public class Test {
    @GetMapping("/test")
    public String fun() {
        System.out.println("success");
        return "success";
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

    }
}
