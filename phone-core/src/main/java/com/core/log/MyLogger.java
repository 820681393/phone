package com.core.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * Created by Miracle on 2018/11/3.
 */
public class MyLogger extends Logger implements Serializable {

    private Logger loggerInfo;
    private Logger loggerDebug;
    private Logger loggerError;
    private Logger loggerTime;
    private Class clazz;
    private Logger loggerObject;
    private String path;

    public MyLogger(Class clazz) {
        super(clazz.getSimpleName());
        this.clazz = clazz;
    }

    public MyLogger(Class clazz, String paht, String name) {
        super(clazz.getSimpleName());
        this.clazz = clazz;
        this.path = paht;
        this.name = name;
        loggerObject = LogInfoTools.getLoggerObjct(clazz, path, name);
    }

    public void info(String massger) {
        if (loggerInfo == null) {
            loggerInfo = getLoggerInfo();
        }
        loggerInfo.setLevel(Level.INFO);
        loggerInfo.info(massger);
        System.out.println(massger);
    }

    public void debug(String massger) {
        if (loggerDebug == null) {
            loggerDebug = getLoggerDebug();
        }
        loggerDebug.setLevel(Level.DEBUG);
        loggerDebug.debug(massger);
        System.out.println(massger);
    }

    public void error(String massger) {
        if (loggerError == null) {
            loggerError = getLoggerError();
        }
        loggerError.setLevel(Level.ERROR);
        loggerError.error(massger);
        System.out.println(massger);
    }

    public void errorFtl(String massger) {
        if (loggerError == null) {
            loggerError = getLoggerFtlError();
        }
        loggerError.setLevel(Level.ERROR);
        loggerError.error(massger);
        System.out.println(massger);
    }

    public void getSqlRunTime(String massger) {
        if (loggerError == null) {
            loggerError = getSqlRunTime();
        }
        loggerError.setLevel(Level.ERROR);
        loggerError.error(massger);
        System.out.println(massger);
    }


    public void errorAspect(String massger) {
        if (loggerError == null) {
            loggerError = getLoggerAspectError();
        }
        loggerError.setLevel(Level.ERROR);
        loggerError.error(massger);
        System.err.println(massger);
    }

    public void time(String massger) {
        if (loggerTime == null) {
            loggerTime = getLoggerTime();
        }
        loggerTime.setLevel(Level.ERROR);
        loggerTime.error(massger);
        System.err.println(massger);
    }

    public void object(String massger) {
        if (loggerObject == null) {
            loggerObject = getLoggerTime();
        }
        loggerObject.setLevel(Level.INFO);
        loggerObject.info(massger);
        System.out.println(massger);
    }

    public Logger getLoggerInfo() {
        loggerInfo = LogInfoTools.getLoggerInfo(clazz);
        return loggerInfo;
    }


    public Logger getLoggerDebug() {
        loggerDebug = LogInfoTools.getLoggerDebug(clazz);
        return loggerDebug;
    }


    public Logger getLoggerError() {
        loggerError = LogInfoTools.getLoggerError(clazz);
        return loggerError;
    }

    public Logger getLoggerFtlError() {
        loggerError = LogInfoTools.getLoggerFtlError(clazz);
        return loggerError;
    }

    public Logger getLoggerAspectError() {
        loggerError = LogInfoTools.getLoggerAspectError(clazz);
        return loggerError;
    }

    public Logger getSqlRunTime() {
        loggerError = LogInfoTools.getSqlRunTime(clazz);
        return loggerError;
    }


    public Logger getLoggerTime() {
        loggerTime = LogInfoTools.getLoggerTime(clazz);
        return loggerTime;
    }


}
