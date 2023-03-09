package com.hhm.doorgod.core.dto;

import com.hhm.doorgod.core.entity.HhmDoorGodRateLimiter;

/**
 * 时间策略
 * @Author huanghm
 * @Date 2023/3/7
 */
public class StrategyAndRateLimiterDto {
    private Long id;
    // 具体限速
    private double limit;
    // 限速器
    private HhmDoorGodRateLimiter hhmDoorGodRateLimiter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public HhmDoorGodRateLimiter getHhmDoorGodRateLimiter() {
        return hhmDoorGodRateLimiter;
    }

    public void setHhmDoorGodRateLimiter(HhmDoorGodRateLimiter hhmDoorGodRateLimiter) {
        this.hhmDoorGodRateLimiter = hhmDoorGodRateLimiter;
    }
}

