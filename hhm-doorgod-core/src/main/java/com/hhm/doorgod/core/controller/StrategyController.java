package com.hhm.doorgod.core.controller;

import com.hhm.doorgod.core.dao.TimeStrategyDao;
import com.hhm.doorgod.core.dto.TimeStrategyDto;
import com.hhm.doorgod.core.entity.ResponseWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *策略controller
 * @Author huanghm
 * @Date 2023/3/8
 */
@RestController
@RequestMapping("/strategy")
public class StrategyController {
    public static final AtomicInteger idAutoIncr = new AtomicInteger(1);
    @PostMapping("/saveOrUpdate")
    public ResponseWrapper addStrategy(@RequestBody TimeStrategyDto timeStrategyDto) {
        Long id = timeStrategyDto.getId();
        if(id == null){
            timeStrategyDto.setId((long) idAutoIncr.getAndIncrement());
            TimeStrategyDao.saveOrUpdate(timeStrategyDto);
        }else {
            final TimeStrategyDto byId = TimeStrategyDao.getById(id);
            if(byId.getStatus() == 2){
                return ResponseWrapper.fail("已过期");
            }
            timeStrategyDto.setStatus(0);
            TimeStrategyDao.saveOrUpdate(timeStrategyDto);
        }

        return ResponseWrapper.success();
    }
}
