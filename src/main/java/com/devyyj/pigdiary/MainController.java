package com.devyyj.pigdiary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String[] main() {
        return new String[]{"m", "a", "i", "n"};
    }
}
