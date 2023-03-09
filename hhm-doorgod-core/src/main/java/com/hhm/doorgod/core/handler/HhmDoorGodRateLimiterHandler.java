package com.hhm.doorgod.core.handler;

import com.hhm.doorgod.core.dto.StrategyAndRateLimiterDto;
import com.hhm.doorgod.core.entity.HhmDoorGodRateLimiter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author huanghm
 * @Date 2023/3/7
 */
public class HhmDoorGodRateLimiterHandler {
    // 限流的url
    private static final Map<String, List<StrategyAndRateLimiterDto>> LIMIT_URL_MAP = new ConcurrentHashMap<>();

    /**
     * 给url设置限流
     * @param url
     * @param strategyAndRateLimiterDto
     */
    public static void putUrlLimit(String url, StrategyAndRateLimiterDto strategyAndRateLimiterDto) {
        List<StrategyAndRateLimiterDto> limiterDtoList = Optional.ofNullable(LIMIT_URL_MAP.get(url)).orElse(new ArrayList<>());
        if (limiterDtoList.isEmpty()) {
            limiterDtoList.add(strategyAndRateLimiterDto);
            LIMIT_URL_MAP.put(url, limiterDtoList);
            return;
        }

        limiterDtoList.add(strategyAndRateLimiterDto);
        // 去重和排序
        final List<StrategyAndRateLimiterDto> sortList = limiterDtoList.stream().collect(Collectors.toMap(StrategyAndRateLimiterDto::getId, Function.identity(), (o1, o2) -> {
            if(o1.getLimit() <= o2.getLimit()){
                return o1;
            }
            return o2;
        })).values().stream().sorted(Comparator.comparing(StrategyAndRateLimiterDto::getLimit)).collect(Collectors.toList());

        LIMIT_URL_MAP.put(url, sortList);
    }

    /**
     * 把url设置为不限流
     * @param url
     */
    public static void removeUrlLimit(String url) {
        LIMIT_URL_MAP.remove(url);
    }

    /**
     * 获取url对应的RateLimiter
     * @param url
     * @return
     */
    public static HhmDoorGodRateLimiter getRateLimiter(String url) {
        final List<StrategyAndRateLimiterDto> limiterDtoList = LIMIT_URL_MAP.get(url);
        if(limiterDtoList != null && limiterDtoList.size() > 0){
            return limiterDtoList.get(0).getHhmDoorGodRateLimiter();
        }
        return null;
    }

}
