package com.example.demo.interceptor;

import com.example.demo.annotation.RequireCookie;
import com.example.demo.entity.User;
import com.example.demo.service.CookieService;
import com.example.demo.model.FailedResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CookieInterceptor implements HandlerInterceptor {
    private final CookieService cookieService;

    @Autowired
    public CookieInterceptor(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequireCookie requireCookie = handlerMethod.getMethodAnnotation(RequireCookie.class);
        
        if (requireCookie == null) {
            requireCookie = handlerMethod.getBeanType().getAnnotation(RequireCookie.class);
        }

        if (requireCookie != null) {
            User cookieUser = cookieService.getUserByCookie(request);
            if (cookieUser == null) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Unauthorized: Cookie validation failed");
                return false;
            }
        }

        return true;
    }
} 