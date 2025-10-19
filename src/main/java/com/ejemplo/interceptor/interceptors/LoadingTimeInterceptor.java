package com.ejemplo.interceptor.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LOG.info("LoadingTimeInterceptor: preHandle entrando..." +  handlerMethod.getMethod().getName());
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        long endTime = System.currentTimeMillis();
        long startTime = (long) request.getAttribute("startTime");
        long result = endTime - startTime;
        LOG.info("Tiempo transcurrido: " + result + " ms");
        LOG.info("LoadingTimeInterceptor: postHandle saliendo...");
    }
}
