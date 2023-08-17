package com.devyyj.pigdiary.security.filter;

import com.devyyj.pigdiary.security.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
@RequiredArgsConstructor
public class ApiCheckfilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("=================================================");
        log.info("=================================================");
        log.info("=================================================");

        if (checkAuthHeader(request)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            // json 리턴
            response.setContentType("application/json;charset=utf-8");
            JSONObject json = new JSONObject();
            String message = "FAIL CHECK API TOKEN";
            json.put("code", "403");
            json.put("message", message);

            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    private boolean checkAuthHeader(HttpServletRequest request) {
        boolean checkResult = false;

        String authHeader = request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            log.info("Authorization exist: " + authHeader);

            try {
                String email = jwtUtil.validateAndExtract(authHeader.substring(7));
                log.info("validate result: " + email);
                checkResult =  email.length() > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return checkResult;
    }
}
