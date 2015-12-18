package com.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogEvent {
    @JsonProperty("IdNumber")
    private int id;
    @JsonProperty("logValue")
    private String logValue;

    public LogEvent() {
    }

    public LogEvent(String logValue) {
        this.logValue = logValue;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogValue() {
        return logValue;
    }

    public void setLogValue(String logValue) {
        this.logValue = logValue;
    }


    @Override
    public String toString() {
        return "LogEvent{" +
                "id=" + id +
                ", logValue='" + logValue + '\'' +
                '}';
    }
}
