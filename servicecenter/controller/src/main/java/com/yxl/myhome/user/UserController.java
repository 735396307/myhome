package com.yxl.myhome.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BG343094
 * @since 2019/8/7 21:29
 **/
@RestController
@RequestMapping("/test")
public class UserController {

    @GetMapping("/tes1t")
    public String fun() {
        return "cusssss";
    }
}
