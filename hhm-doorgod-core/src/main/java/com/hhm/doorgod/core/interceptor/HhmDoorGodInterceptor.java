package com.hhm.doorgod.core.interceptor;

import com.alibaba.fastjson.JSON;
import com.hhm.doorgod.core.entity.HhmDoorGodRateLimiter;
import com.hhm.doorgod.core.entity.ResponseWrapper;
import com.hhm.doorgod.core.handler.HhmDoorGodRateLimiterHandler;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author huanghm
 * @Date 2023/3/6
 */
public class HhmDoorGodInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        HhmDoorGodRateLimiter limiter = HhmDoorGodRateLimiterHandler.getRateLimiter(uri);
        boolean b = (limiter == null || limiter.tryAcquire());
        if(!b){
           writeLimitBody(response);
        }
        return b;
    }

    private void writeLimitBody(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(200);
        response.getWriter().write(JSON.toJSONString(ResponseWrapper.limitResponse()));
    }
}
