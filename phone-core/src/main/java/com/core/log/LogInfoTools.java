package com.core.log;

import com.core.utils.MyDateUtil;
import org.apache.log4j.*;
import org.apache.log4j.varia.LevelRangeFilter;

import java.util.Date;

/**
 * Created by Miracle on 2018/11/3.
 */
public class LogInfoTools {

    public static Logger getLoggerInfo(Class clazz) {
        StringBuffer logFilePath = new StringBuffer(System.getProperty("user.dir") + "/logs/");
        logFilePath.append(MyDateUtil.simpleDateString(new Date(), "yyyy-MM-dd"));
        logFilePath.append("/");
        logFilePath.append(clazz.getSimpleName());
        logFilePath.append("/");
        logFilePath.append(clazz.getCanonicalName());
        logFilePath.append("--info.log");
        Logger logger = LogManager.getLogger(clazz);
        Layout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p [%t] %c.%M(%L) - %m%n");
        try {
            Appender appender = new FileAppender(layout, logFilePath.toString());

            LevelRangeFilter f = new LevelRangeFilter();
            f.setLevelMin(Level.INFO);
            f.setLevelMax(Level.INFO);
            appender.addFilter(f);
            logger.addAppender(appender);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.setLevel(Level.INFO);
        return logger;
    }

    public static Logger getLoggerDebug(Class clazz) {
        StringBuffer logFilePath = new StringBuffer(System.getProperty("user.dir") + "/logs/");
        logFilePath.append(MyDateUtil.simpleDateString(new Date(), "yyyy-MM-dd"));
        logFilePath.append("/");
        logFilePath.append(clazz.getSimpleName());
        logFilePath.append("/");
        logFilePath.append(clazz.getCanonicalName());
        logFilePath.append("--debug.log");
        Logger logger = LogManager.getLogger(clazz);
        Layout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p [%t] %c.%M(%L) - %m%n");
        try {
            Appender appender = new FileAppender(layout, logFilePath.toString());
            LevelRangeFilter f = new LevelRangeFilter();
            f.setLevelMin(Level.ALL);
            f.setLevelMax(Level.OFF);
            appender.addFilter(f);
            logger.addAppender(appender);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.setLevel(Level.DEBUG);
        return logger;
    }

    public static Logger getLoggerError(Class clazz) {
        StringBuffer logFilePath = new StringBuffer(System.getProperty("user.dir") + "/logs/");
        logFilePath.append(MyDateUtil.simpleDateString(new Date(), "yyyy-MM-dd"));
        logFilePath.append("/");
        logFilePath.append(clazz.getSimpleName());
        logFilePath.append("/");
        logFilePath.append(clazz.getCanonicalName());
        logFilePath.append("--error.log");
        Logger logger = LogManager.getLogger(clazz);
        Layout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p [%t] %c.%M(%L) - %m%n");
        try {
            Appender appender = new FileAppender(layout, logFilePath.toString());
            LevelRangeFilter f = new LevelRangeFilter();
            f.setLevelMin(Level.ERROR);
            f.setLevelMax(Level.ERROR);
            appender.addFilter(f);
            logger.addAppender(appender);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.setLevel(Level.ERROR);
        return logger;
    }

    public static Logger getLoggerTime(Class clazz) {
        StringBuffer logFilePath = new StringBuffer(System.getProperty("user.dir") + "/logs/");
        logFilePath.append(MyDateUtil.simpleDateString(new Date(), "yyyy-MM-dd"));
        logFilePath.append("/");
        logFilePath.append("api-time.log");
        Logger logger = LogManager.getLogger(clazz);
        Layout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p [%t] %c.%M(%L) - %m%n");
        try {
            Appender appender = new FileAppender(layout, logFilePath.toString());
            LevelRangeFilter f = new LevelRangeFilter();
            f.setLevelMin(Level.ERROR);
            f.setLevelMax(Level.ERROR);
            appender.addFilter(f);
            logger.addAppender(appender);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.setLevel(Level.ERROR);
        return logger;
    }

    /**
     * @param clazz 类加载器
     * @param paht  日志储存路径  例如：info/test
     * @param name  日志名字 Test
     * @return logger日志对象
     * 返回案例存储位置：logs/info/test/Test.log
     */
    public static Logger getLoggerObjct(Class clazz, String paht, String name) {
        StringBuffer logFilePath = new StringBuffer(System.getProperty("user.dir") + "/logs/");
        logFilePath.append(MyDateUtil.simpleDateString(new Date(), "yyyy-MM-dd"));
        logFilePath.append("/");
        logFilePath.append(paht);
        logFilePath.append("/");
        logFilePath.append(name);
        logFilePath.append(".log");
        Logger logger = LogManager.getLogger(clazz);
        Layout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p [%t] %c.%M(%L) - %m%n");
        try {
            Appender appender = new FileAppender(layout, logFilePath.toString());
            LevelRangeFilter f = new LevelRangeFilter();
            f.setLevelMin(Level.INFO);
            f.setLevelMax(Level.INFO);
            appender.addFilter(f);
            logger.addAppender(appender);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.setLevel(Level.INFO);
        return logger;
    }


    public static Logger getLoggerFtlError(Class clazz) {
        StringBuffer logFilePath = new StringBuffer(System.getProperty("user.dir") + "/logs/");
        logFilePath.append(MyDateUtil.simpleDateString(new Date(), "yyyy-MM-dd"));
        logFilePath.append("/");
        logFilePath.append("ftl-error.log");
        Logger logger = LogManager.getLogger(clazz);
        Layout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p [%t] %c.%M(%L) - %m%n");
        try {
            Appender appender = new FileAppender(layout, logFilePath.toString());
            LevelRangeFilter f = new LevelRangeFilter();
            f.setLevelMin(Level.ERROR);
            f.setLevelMax(Level.ERROR);
            appender.addFilter(f);
            logger.addAppender(appender);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.setLevel(Level.ERROR);
        return logger;
    }


    public static Logger getLoggerAspectError(Class clazz) {
        StringBuffer logFilePath = new StringBuffer(System.getProperty("user.dir") + "/logs/");
        logFilePath.append(MyDateUtil.simpleDateString(new Date(), "yyyy-MM-dd"));
        logFilePath.append("/");
        logFilePath.append("system-error.log");
        Logger logger = LogManager.getLogger(clazz);
        Layout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p [%t] %c.%M(%L) - %m%n");
        try {
            Appender appender = new FileAppender(layout, logFilePath.toString());
            LevelRangeFilter f = new LevelRangeFilter();
            f.setLevelMin(Level.ERROR);
            f.setLevelMax(Level.ERROR);
            appender.addFilter(f);
            logger.addAppender(appender);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.setLevel(Level.ERROR);
        return logger;
    }


    public static Logger getSqlRunTime(Class clazz) {
        StringBuffer logFilePath = new StringBuffer(System.getProperty("user.dir") + "/logs/");
        logFilePath.append(MyDateUtil.simpleDateString(new Date(), "yyyy-MM-dd"));
        logFilePath.append("/");
        logFilePath.append("sql-time-out.log");
        Logger logger = LogManager.getLogger(clazz);
        Layout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p [%t] %c.%M(%L) - %m%n");
        try {
            Appender appender = new FileAppender(layout, logFilePath.toString());
            LevelRangeFilter f = new LevelRangeFilter();
            f.setLevelMin(Level.ERROR);
            f.setLevelMax(Level.ERROR);
            appender.addFilter(f);
            logger.addAppender(appender);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.setLevel(Level.ERROR);
        return logger;
    }

}
