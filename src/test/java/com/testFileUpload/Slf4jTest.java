package com.testFileUpload;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {
    private static Logger logger = LoggerFactory.getLogger(Slf4jTest.class);// slf4j日志记录器

    public static void main(String[] args) {

       /* // 普通的日志记录
        logger.debug("普通的日志记录");

        logger.info("日志");

        // {}占位符记录日志
        for (int i = 0; i < 3; i++) {
            logger.debug("这是第{}条记录", i);
        }

        // 用\转义{}
        logger.debug("Set \\{} differs from {}", "3"); // output:Set {} differs
        // from 3

        // 两个参数
        logger.debug("两个占位符，可以传两个参数{}----{}", 1, 2);

        // 多个参数(可变参数)
        logger.debug("debug:多个占位符，{},{},{},{}", 1, 2, 3, 4);

        // 多个参数(可变参数)
        logger.info("info:多个占位符，{},{},{},{}", 1, 2, 3, 4);

        // 多个参数(可变参数)
        logger.error("error:多个占位符，{},{},{},{}", 1, 2, 3, 4);
    }*/
        for (int i = 0; i < 3; i++) {
            logger.info("logback So" + i);
            logger.error("logback Down");
            logger.debug("debug");
        }

        logger.info("logback So44");
    }
}
