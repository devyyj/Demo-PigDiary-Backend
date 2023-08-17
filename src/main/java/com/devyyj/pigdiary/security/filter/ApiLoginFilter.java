package com.devyyj.pigdiary.security.filter;

import com.devyyj.pigdiary.security.dto.MemberDTO;
import com.devyyj.pigdiary.security.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {
    private final JWTUtil jwtUtil;
    public ApiLoginFilter(String defaultFilterProcessesUrl, JWTUtil jwtUtil) {
        super(defaultFilterProcessesUrl);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("===================================ApiLoginFilter=============================");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

//        return null;

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = getAuthenticationManager().authenticate(token);
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);

        log.info("successfulAuthentication : " + authResult);

        String email = ((MemberDTO)authResult.getPrincipal()).getEmail();

        String token = null;

        try {
            token = jwtUtil.generateToken(email);

            response.setContentType("text/plain");
            response.getOutputStream().write(token.getBytes());

            log.info(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        super.unsuccessfulAuthentication(request, response, failed);

        log.info("login fail handler................");
        log.info(failed.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // json 리턴
        response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();
        String message = failed.getMessage();
        json.put("code", "401");
        json.put("message", message);

        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
