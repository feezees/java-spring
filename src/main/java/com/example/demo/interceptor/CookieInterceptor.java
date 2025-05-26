package com.example.demo.interceptor;

import com.example.demo.annotation.RequireCookie;
import com.example.demo.entity.Cookies;
import com.example.demo.model.FailedResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CookieInterceptor implements HandlerInterceptor {
    private final Cookies cookies = new Cookies();

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
            String cookieUser = cookies.getCookiesUser(request);
            if ("undefined".equals(cookieUser)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Unauthorized: Cookie validation failed");
                return false;
            }
        }

        return true;
    }
} 