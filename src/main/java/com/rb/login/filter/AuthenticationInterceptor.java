package com.rb.login.filter;

import com.rb.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token验证拦截器
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    public static final String TOKEN_NAME = "access_token";
    private UserService userService;
    public AuthenticationInterceptor(UserService userService) {
        this.userService = userService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("/".equals(request.getRequestURI())) {
            return true;
        }
        return userAuth(request);
    }
    /**
     * 实际用户token鉴权
     *
     * @param request
     */
    private boolean userAuth(HttpServletRequest request) {
        String tokenId = getTokenIdFromParamters(request);
        /*if (StringUtils.isEmpty(tokenId)) {
            throw new BusinessException("您尚未登录,请登录系统！");
        }*/
        /**
         * 这里手动实现权限验证，用户-角色-资源
         */
        //获取访问的url
        String url = request.getRequestURI();
        String method = request.getMethod().toUpperCase();
        log.info("url:"+url);
        log.info("method:"+method);
        return userService.checkLoginUser(url,tokenId,method);
    }
    /**
     * 从参数中获取tokenid
     *
     * @param request
     * @return
     */
    private String getTokenIdFromParamters(HttpServletRequest request) {
        String tokenId = request.getParameter(TOKEN_NAME);
        return tokenId;
    }
    /**
     * 从header获取tokenid
     *
     * @param request
     * @return
     */
    private String getTokenIdFromHeader(HttpServletRequest request) {
        String tokenId = request.getHeader(TOKEN_NAME);
        return tokenId;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}

