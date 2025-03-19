package com.daiweij.myvedio.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    /**
     * 根据传入的类获取对应的logger实例
     *
     * @param clazz 目标类
     * @return Logger 实例
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * 记录info级日志
     *
     * @param clazz   调用类
     * @param message 日志内容
     */
    public static void info(Class<?> clazz, String message) {
        Logger logger = getLogger(clazz);
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

    /**
     * 记录 Debug 级别日志
     *
     * @param clazz   调用类
     * @param message 日志内容
     */
    public static void debug(Class<?> clazz, String message) {
        Logger logger = getLogger(clazz);
        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }

    /**
     * 记录 Warn 级别日志
     *
     * @param clazz   调用类
     * @param message 日志内容
     */
    public static void warn(Class<?> clazz, String message) {
        Logger logger = getLogger(clazz);
        if (logger.isWarnEnabled()) {
            logger.warn(message);
        }
    }

    /**
     * 记录 Error 级别日志（带异常信息）
     *
     * @param clazz     调用类
     * @param message   日志内容
     * @param throwable 异常信息
     */
    public static void error(Class<?> clazz, String message, Throwable throwable) {
        Logger logger = getLogger(clazz);
        logger.error(message, throwable);
    }

    /**
     * 记录 Error 级别日志（不带异常信息）
     *
     * @param clazz   调用类
     * @param message 日志内容
     */
    public static void error(Class<?> clazz, String message) {
        Logger logger = getLogger(clazz);
        logger.error(message);
    }
}
