package com.devyyj.pigdiary.user.controller;

import com.devyyj.pigdiary.user.dto.UserDTO;
import com.devyyj.pigdiary.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 가입 유무를 확인해서 가입시킨다
     * @param userDTO
     * @return 유저 닉네임
     */
    @PostMapping
    ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        String nick_name = userService.getUser(userDTO);

        if (nick_name == null) {
            userService.createUser(userDTO);
            nick_name = userDTO.getNickName();
        }

        return new ResponseEntity<>(nick_name, HttpStatus.CREATED);
    }
}
