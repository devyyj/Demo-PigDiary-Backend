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
    public void testEncode() {
        String pw = "1111";
        String enpw = passwordEncoder.encode(pw);

        System.out.println(enpw);

        boolean matchResult = passwordEncoder.matches(pw, enpw);

        System.out.println(matchResult);
    }


}
