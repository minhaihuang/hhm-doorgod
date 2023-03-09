package com.hhm.doorgod.core.dao;

import com.hhm.doorgod.core.dto.TimeStrategyDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author huanghm
 * @Date 2023/3/8
 */
public class TimeStrategyDao {
    public static final Map<Long, TimeStrategyDto> timeStrategyDtos = new ConcurrentHashMap<>();

    public static boolean saveOrUpdate(TimeStrategyDto timeStrategyDto) {
        timeStrategyDtos.put(timeStrategyDto.getId(), timeStrategyDto);
        return true;
    }

    public static TimeStrategyDto getById(Long id) {
        return timeStrategyDtos.get(id);
    }

    public static List<TimeStrategyDto> listAll() {
        return new ArrayList<>(timeStrategyDtos.values());
    }
}
