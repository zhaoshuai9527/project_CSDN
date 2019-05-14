package com.zs.interceptor;

import com.zs.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    //@Bean在config.JwtConfiguration中
    @Autowired
    private JwtUtil jwtUtil;

    //处理权限问题：给request请求贴角色标签 （admin或user）

    //拦截器 四种方法：重写其中的一种实现拦截功能
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("开启拦截器...");
        //前缀 +token == Authrorization
        String authrorization = request.getHeader("Authrorization");
        //判断authrorization是否为空，  字符串.startsWith（开头）是否为"Bearer "
        if (authrorization != null && authrorization.startsWith("Bearer ")) {
            //获取token
            String token = authrorization.substring(7);
            //解析token
            Claims claims = jwtUtil.parseJwt(token);
            //claims中包含id，date，roles。。。
            if (claims != null) {
                //claims.get("roles").equals("admin"),可能会有空指针异常
                if ("admin".equals(claims.get("roles"))) {
                    //如果是admin，标识为admin_claims
                    request.setAttribute("admin_claims", claims);

                } else if ("user".equals(claims.get("roles"))) {
                    //如果是user，标识为user_claims
                    request.setAttribute("user_claims", claims);
                } else {
                    throw new RuntimeException("角色有误！");
                }
            }
            return true;
        }
        return true;//放行

    }
}

