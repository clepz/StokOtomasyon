package com.tarikaskin.stok.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private String realm = "denemerealm";
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.addHeader("WWW-Authentication","Basic realm= " + this.realm);
        System.out.println(httpServletRequest.getHeader("Authorization"));
        PrintWriter writer = httpServletResponse.getWriter();
        writer.println("Http Status 401 :" + e.getMessage());
    }
}
