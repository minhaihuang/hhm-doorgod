package com.hhm.doorgod.core.monitor;

import com.google.common.util.concurrent.RateLimiter;
import com.hhm.doorgod.core.dao.TimeStrategyDao;
import com.hhm.doorgod.core.dto.StrategyAndRateLimiterDto;
import com.hhm.doorgod.core.dto.TimeStrategyDto;
import com.hhm.doorgod.core.entity.HhmDoorGodRateLimiter;
import com.hhm.doorgod.core.handler.HhmDoorGodRateLimiterHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Date;
import java.util.List;

/**
 * @Author huanghm
 * @Date 2023/3/8
 */
public class TimeStrategyMonitor implements ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext;
    @Override
    public void afterPropertiesSet(){
        Runnable runnable = () -> {
            while (true){
                try {
                    final List<TimeStrategyDto> timeStrategyDtos = TimeStrategyDao.listAll();
                    Date now = new Date();
                    for(TimeStrategyDto timeStrategyDto: timeStrategyDtos) {
                        if (timeStrategyDto.getStatus() == 0) {
                            if(now.after(timeStrategyDto.getBeginTime()) && now.before(timeStrategyDto.getEndTime())) {
                                // 解析参数
                                final List<TimeStrategyDto.LimitAndUrl> limitList = timeStrategyDto.getLimitList();
                                for(TimeStrategyDto.LimitAndUrl limitAndUrl: limitList) {
                                    double limit = limitAndUrl.getTimes()/ (double) limitAndUrl.getSeconds();
                                    final HhmDoorGodRateLimiter rateLimiter = HhmDoorGodRateLimiter.create(limit);
                                    StrategyAndRateLimiterDto strategyAndRateLimiterDto = new StrategyAndRateLimiterDto();
                                    strategyAndRateLimiterDto.setId(timeStrategyDto.getId());
                                    strategyAndRateLimiterDto.setLimit(limit);
                                    strategyAndRateLimiterDto.setHhmDoorGodRateLimiter(rateLimiter);
                                    final String[] split = limitAndUrl.getUrls().split(",");
                                    for(String url: split) {
                                        HhmDoorGodRateLimiterHandler.putUrlLimit(url, strategyAndRateLimiterDto);
                                    }
                                }
                                timeStrategyDto.setStatus(1);
                            }

                        }else if(timeStrategyDto.getStatus() == 1) {
                            if(now.after(timeStrategyDto.getEndTime())) {
                                // 解析参数
                                final List<TimeStrategyDto.LimitAndUrl> limitList = timeStrategyDto.getLimitList();
                                for(TimeStrategyDto.LimitAndUrl limitAndUrl: limitList) {
                                    final String[] split = limitAndUrl.getUrls().split(",");
                                    for(String url: split) {
                                        HhmDoorGodRateLimiterHandler.removeUrlLimit(url);
                                    }
                                }
                                timeStrategyDto.setStatus(2);
                            }
                        }
                    }
                    Thread.sleep(1000L);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
