package com.hhm.doorgod.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 时间策略
 * @Author huanghm
 * @Date 2023/3/7
 */
public class TimeStrategyDto {
    private Long id;
    // 策略开始时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    // 策略结束时间
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    // 限速集合
    private List<LimitAndUrl> limitList;
    // 状态
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<LimitAndUrl> getLimitList() {
        return limitList;
    }

    public void setLimitList(List<LimitAndUrl> limitList) {
        this.limitList = limitList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class LimitAndUrl{
        // url集合，逗号分隔
        private String urls;
        // 秒数
        private int seconds;
        // 次数
        private int times;

        public String getUrls() {
            return urls;
        }

        public void setUrls(String urls) {
            this.urls = urls;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }
    }
}

