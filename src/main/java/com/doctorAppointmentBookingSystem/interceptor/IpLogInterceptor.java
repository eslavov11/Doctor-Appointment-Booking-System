package com.doctorAppointmentBookingSystem.interceptor;

import com.doctorAppointmentBookingSystem.entity.Log;
import com.doctorAppointmentBookingSystem.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class IpLogInterceptor extends HandlerInterceptorAdapter {
    private final LogService logService;

    @Autowired
    public IpLogInterceptor(LogService logService) {
        this.logService = logService;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String message = String.format("[%s - %s]",
                handlerMethod.getBean().getClass(),
                handlerMethod.getMethod().getName());

        String ipAddress = request.getRemoteAddr();

        Log log = new Log();
        log.setDate(new Date());
        log.setIp(ipAddress);
        log.setText(message);
        this.logService.save(log);
    }
}
