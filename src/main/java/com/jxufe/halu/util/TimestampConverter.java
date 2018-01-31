package com.jxufe.halu.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimestampConverter implements Converter<String,Timestamp> {

    public Timestamp convert(String s) {
        if(!StringUtils.isEmpty(s.trim())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            try {
                timestamp = new Timestamp(sdf.parse(s).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return timestamp;
        }
        return null;
    }
}
