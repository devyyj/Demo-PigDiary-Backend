package com.devyyj.pigdiary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
//        @PreAuthorize("hasRole('USER')")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @PreAuthorize("hasAuthority('OAUTH2_USER')")
    public String root() {
        return "root";
    }
    @GetMapping("/all")
    public String all() {
        return "all";
    }

    @GetMapping("/member")
//    @PreAuthorize("hasRole('USER')")
//    @PreAuthorize("hasAuthority('OAUTH2_USER')")
    public String member() {
        return "member";
    }

    @GetMapping("/admin")
//    @PreAuthorize("hasAuthority('OAUTH2_USER')")
    public String admin() {
        return "admin";
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("login", HttpStatus.OK);
    }

}
