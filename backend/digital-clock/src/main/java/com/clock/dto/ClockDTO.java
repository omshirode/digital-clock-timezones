package com.clock.dto;

public class ClockDTO {
    private String timezone;
    private String time;
    private String date;
    private String offset;

    public ClockDTO(String timezone, String time, String date, String offset) {
        this.timezone = timezone;
        this.time = time;
        this.date = date;
        this.offset = offset;
    }

    public ClockDTO() {}

    // Getters and Setters
    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getOffset() { return offset; }
    public void setOffset(String offset) { this.offset = offset; }
}