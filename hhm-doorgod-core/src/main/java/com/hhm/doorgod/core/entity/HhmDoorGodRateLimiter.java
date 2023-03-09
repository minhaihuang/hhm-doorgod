package com.hhm.doorgod.core.entity;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * rateLimit装饰类
 * @Author huanghm
 * @Date 2023/3/9
 */
public class HhmDoorGodRateLimiter {
    private RateLimiter rateLimiter;
    private double limit;

    public static HhmDoorGodRateLimiter create(double limit) {
        if (limit <= 0.0) {
            return new HhmDoorGodRateLimiter(null, 0);
        }
        return new HhmDoorGodRateLimiter(RateLimiter.create(limit), limit);

    }

    public boolean tryAcquire() {
        if(this.limit == 0.0) {
            return false;
        }
        return this.rateLimiter.tryAcquire();
    }

    public boolean tryAcquire(long timeout, TimeUnit unit) {
        if(this.limit == 0.0) {
            return false;
        }
        return this.rateLimiter.tryAcquire(1, timeout, unit);
    }

    public boolean tryAcquire(int permits) {
        if(this.limit == 0.0) {
            return false;
        }
        return this.rateLimiter.tryAcquire(permits, 0L, TimeUnit.MICROSECONDS);

    }

    private HhmDoorGodRateLimiter(RateLimiter rateLimiter, double limit) {
        this.rateLimiter = rateLimiter;
        this.limit = limit;
    }

}
