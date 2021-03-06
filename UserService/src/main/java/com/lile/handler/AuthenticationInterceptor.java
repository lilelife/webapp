package com.lile.handler;

import com.lile.common.mybits.model.User;
import com.lile.service.UserService;
import exceptions.UncheckedException;
import io.jsonwebtoken.JwtException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import utils.ErrorCode;
import utils.JwtTokenUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName AuthenticationInterceptor
 * @Description TODO
 * @Author lile
 * @Date 2020/2/10 13:48
 * @Version 1.0
 */
public class AuthenticationInterceptor  implements HandlerInterceptor {
    @Resource
    private UserService userService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new UncheckedException(ErrorCode.LOGIN_FAILURE,"无token,请重新登录");
                }
                // 获取 token 中的 phone ，phone唯一
                String phone;
                try {
                    phone = jwtTokenUtil.getUsernameFromToken(token);
                } catch (JwtException j) {
                    throw new UncheckedException(ErrorCode.PERMISSION_DENIED,"token错误，权限不足");
                }
                User user = userService.getUserById(Integer.parseInt(phone));
                if (user == null) {
                    throw new UncheckedException(ErrorCode.PERMISSION_DENIED,"用户不存在，重新登录");

                }
                // 验证 token
                if(!jwtTokenUtil.isTokenExpired(token)&& jwtTokenUtil.generateTokenByUsername(phone).equals(token)){
                    throw new UncheckedException(ErrorCode.PERMISSION_DENIED,"token过期或不正确");
                }

                return true;
            }
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
