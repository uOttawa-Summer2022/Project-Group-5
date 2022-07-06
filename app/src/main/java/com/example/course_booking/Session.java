package com.example.course_booking;

public class Session {

    private Day day;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;

    //constructors
    public Session(Day day, int startHour, int startMinute, int endHour, int endMinute) {
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }


    //getters and setters
    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int time) {
        this.startHour = time;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int time) {
        this.startMinute = time;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int time) { this.endHour = time; }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int time) {
        this.endMinute = time;
    }

    @Override
    public String toString() {
        // will look like -> "MONDAY;8;30;11;00", Necessary for simpler editing when fetched from the database as a string.
        return "" + day + ";" + startHour + ";" + startMinute + ";" + endHour + ";" + endMinute;
    }
}