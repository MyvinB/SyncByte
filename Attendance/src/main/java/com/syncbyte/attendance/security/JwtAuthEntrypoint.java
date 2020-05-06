package com.syncbyte.attendance.security;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/*
 @author Myvin Barboza
 30/04/20 1:05 PM 
 */



@Component
public class JwtAuthEntrypoint implements AuthenticationEntryPoint {


    private static final Logger LOGGER= LoggerFactory.getLogger(JwtAuthEntrypoint.class);

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        LOGGER.error("UNAUTHORISED MESSAGE",e.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Error-.Unauthorized");

    }

}