package com.kennedy.herostory;

import com.kennedy.herostory.mq.MQConsumer;
import com.kennedy.herostory.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 排行榜应用程序
 */
public class RankApp {

    /**
     * 日志对象
     */
    static private final Logger LOGGER = LoggerFactory.getLogger(RankApp.class);

    /**
     * 应用主函数
     *
     * @param argvArray 命令行参数数组
     */
    static public void main(String[] argvArray) {
        RedisUtil.init();
        MQConsumer.init();

        LOGGER.info("排行榜应用程序启动成功!");
    }
}
