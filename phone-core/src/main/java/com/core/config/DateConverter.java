package com.core.config;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by ben on 2017-03-07 0007.
 */
public class DateConverter implements Converter<String, Date> {

    private static final String[] patterns = {"yyyyMMddHHmmss","yyyyMMdd","HHmmss","yyyyMMddHHmm","yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "HH:mm:ss"};

    @Override
    public Date convert(String s) {
        if (s == null || s.trim().equals("")) {
            return null;
        }
        try {
            return DateUtils.parseDate(s, patterns);
        } catch (ParseException e) {
            throw new IllegalArgumentException("字符串转日期失败", e);
        }
    }
}

