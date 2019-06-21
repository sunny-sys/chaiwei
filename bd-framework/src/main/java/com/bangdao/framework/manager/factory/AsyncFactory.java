package com.bangdao.framework.manager.factory;

import com.bangdao.common.utils.AddressUtils;
import com.bangdao.common.utils.spring.SpringUtils;
import com.bangdao.domain.system.OperLog;
import com.bangdao.service.system.IOperLogService;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 */
@Slf4j
public class AsyncFactory {

    /**
     * 操作日志记录
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final OperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(IOperLogService.class).insertOperlog(operLog);
                log.info("write operLog info to db");
            }
        };
    }
}
