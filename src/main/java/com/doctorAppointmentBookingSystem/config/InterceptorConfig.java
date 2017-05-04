//package com.doctorAppointmentBookingSystem.config;
//
//import com.doctorAppointmentBookingSystem.interceptor.IpLogInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class InterceptorConfig extends WebMvcConfigurerAdapter {
//    private final IpLogInterceptor ipLogInterceptor;
//
//    @Autowired
//    public InterceptorConfig(IpLogInterceptor ipLogInterceptor) {
//        this.ipLogInterceptor = ipLogInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
//        registry.addInterceptor(this.ipLogInterceptor);
//    }
//}
