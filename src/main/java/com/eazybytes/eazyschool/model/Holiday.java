package com.eazybytes.eazyschool.model;

import lombok.Data;

@Data
public class Holiday {
    private final String day;
    private final String reason;
    private final Type type;

    public enum Type
    {
        FESTIVAL, FEDERAL
    }
//
//    public Holiday(String day, String reason, Type type) {
//        this.day = day;
//        this.reason = reason;
//        this.type = type;
//    }
//
//    public Type getType() {
//        return type;
//    }
//
//    public String getReason() {
//        return reason;
//    }
//
//    public String getDay() {
//        return day;
//    }
}
