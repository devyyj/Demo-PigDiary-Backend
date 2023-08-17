package com.devyyj.pigdiary.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;

import java.util.Date;


@Log4j2
public class JWTUtil {

    private String secretKey = "zerock12345678";

    //1month
    private long expire = 60 * 24* 30;

    public String generateToken(String content) throws Exception{
        Date issuedAt = new Date();

        // 예를 위해 발급 시간으로부터 1시간 뒤를 만료 시간으로 설정
        Date expiration = new Date(issuedAt.getTime() + 3600000); // 1시간

        return JWT.create()
                .withIssuedAt(issuedAt)     // 발급 시간 설정
                .withExpiresAt(expiration)  // 만료 시간 설정
                .withSubject(content)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String validateAndExtract(String token)throws Exception {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);

            return decodedJWT.getSubject(); // subject 정보 리턴
        } catch (Exception e) {
            // 검증 실패 시 예외 처리
            e.printStackTrace();
            return null;
        }
    }

}
