package com.devyyj.pigdiary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class PigdiaryApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEndoe(){
        String password = "1111";
        String enPw = passwordEncoder.encode(password);
        System.out.println("enPw = " + enPw);
        System.out.println("matches = " + passwordEncoder.matches(password, enPw));
    }
}
