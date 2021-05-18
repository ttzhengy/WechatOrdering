package com.yat.wechatorderingsystem.logger;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class LoggerTest {

    @Test
    public void loggerTest1(){
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warm");
        log.error("error");
    }

    @Test
    public void loggerTest2(){
        log.info("test from {}","YAT");
        String path = LoggerTest.class.getResource("/").toString();
        log.info("classpath => {}",path);
    }
}
