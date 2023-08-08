package com.devyyj.pigdiary.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class ApiCheckfilter extends OncePerRequestFilter {
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

        if (authHeader != null) {
            log.info("header is " + authHeader);
            if (authHeader.equals("12345")) {
                checkResult = true;
            }
        }

        return checkResult;
    }
}
