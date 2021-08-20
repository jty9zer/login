package com.rb.login.config;

import com.rb.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    /**
     * 配置.忽略的静态文件，不加的话，登录之前页面的css,js不能正常使用，得登录之后才能正常.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略URL
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    /**
     * 重写authenticationManagerBean方法,防止注入失败
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**/*.js", "/**/*.html", "/**/*.css", "/oauth/**","/oauth2/**", "/**/*.jpg", "/**/*.png", "/**/*.ttf", "/**/*.woff", "/**/*.woff2").permitAll()
                //其他的请求都必须要有权限认证
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .successHandler(myAuthenticationSuccessHandler)
                .and()
                .formLogin() //允许用户进行基于表单的认证
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler)
                .and()
                // 暂时禁用CSRF，否则无法提交登录表单
                .csrf().disable()
                .oauth2Client();
        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
