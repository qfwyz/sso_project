package com.hwua.test;

import com.hwua.config.MyRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerTest01 {
    private static Logger logger = LoggerFactory.getLogger(MyRealm.class);
    public static void main(String[] args) {
        logger.info("hello info");
        logger.warn("hello info");
        logger.error("hello info");
        logger.debug("hello info");

    }
}
