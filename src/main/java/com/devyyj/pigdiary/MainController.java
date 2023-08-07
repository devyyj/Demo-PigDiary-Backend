package com.devyyj.pigdiary;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String root() {
        return "root";
    }
    @GetMapping("/all")
    public String all() {
        return "all";
    }

    @GetMapping("/member")
    public String member() {
        return "member";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
