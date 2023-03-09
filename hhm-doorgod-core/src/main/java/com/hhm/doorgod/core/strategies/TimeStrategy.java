package com.hhm.doorgod.core.strategies;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间策略
 * @Author huanghm
 * @Date 2023/3/7
 */

public class TimeStrategy {
    // 策略开始时间
    private Date beginTime;
    // 策略结束时间
    private Date endTime;
    // 状态。0-新建，1-生效中，2-已过期
    private int status;
    // 限流的url
    private Map<String, RateLimiter> limitUrlMap = new HashMap<>();

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Map<String, RateLimiter> getLimitUrlMap() {
        return limitUrlMap;
    }

    public void setLimitUrlMap(Map<String, RateLimiter> limitUrlMap) {
        this.limitUrlMap = limitUrlMap;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

