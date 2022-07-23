package com.example.course_booking;


import android.util.Log;
import android.view.ViewDebug;

public class Validator {

    String usernameR = "[A-Za-z]+";
    String crsCodeR = "[A-Z]{3}[0-9]{4}";


    public Validator() {}

    public boolean validUsername(String username) {
        return username.matches(usernameR);
    }

    public boolean validCrsCode(String crsCode) {
        return crsCode.matches(crsCodeR);
    }

    public boolean validSession(int startHour , int startMin, int endHour, int endMin){

        if(startHour < 0 || startHour > 23){return false;}

        if(startMin < 0 || startMin > 59){return false;}

        if(endHour < 0 || endHour > 23){return false;}

        return endMin >= 0 && endMin <= 59;
    }

    public boolean validDay(String day) {
        for (Day d : Day.values()) {
            if (d.name().equals(day)) {
                return true;
            }
        }

        return false;
    }

    public boolean validTime(Session s1, Session s2)  {

        if (!s1.getDay().equals(s2.getDay())) {
            return true;
        }
        else if (s1.getStartHour() < s2.getStartHour() && s1.getEndHour() > s2.getStartHour()) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean validCapacity(CourseModel cm) {
        if (cm.getCrsCapacity() > 0) {
            return true;
        }
        return false;
    }

}
