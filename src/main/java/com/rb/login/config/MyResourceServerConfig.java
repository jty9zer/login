package com.rb.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器
 **/
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;
    @Autowired
    private GithubAuthenticationSuccessHandler githubAuthenticationSuccessHandler;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**/*.js","/**/*.html","/**/*.css", "/oauth2/**","/oauth/**","/**/*.jpg","/**/*.png","/**/*.ttf","/**/*.woff","/**/*.woff2").permitAll()
                //其他的请求都必须要有权限认证
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .successHandler(githubAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)
                .and()
                .formLogin()//允许用户进行基于表单的认证
                .loginPage("/login.html")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)
                .and()
                .headers().frameOptions().disable()
                .and()
                // 暂时禁用CSRF，否则无法提交登录表单
                .csrf().disable();
    }
    public MyAuthenticationSuccessHandler loginSuccessHandler(){
        return new MyAuthenticationSuccessHandler();
    }
}

