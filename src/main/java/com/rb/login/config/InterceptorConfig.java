package com.rb.login.config;

import com.rb.login.filter.AuthenticationInterceptor;
import com.rb.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private UserService userService;

//    @Resource
//    private AuthenticationInterceptor authenticationInterceptor;

    private String exclusions = "/oauth2/**,/login,/oauth/token,/**/*.html,/**/*.js,/**/*.css,/**/*.jpg,/**/*.png,/**/*.ttf,/**/*.woff,/**/*.woff2";

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new AuthenticationInterceptor(userService));
        //不验证权限
        String[] array = exclusions.split(",");
        if(array.length>0){
            interceptorRegistration.excludePathPatterns(Arrays.asList(array));
        }
    }

}
