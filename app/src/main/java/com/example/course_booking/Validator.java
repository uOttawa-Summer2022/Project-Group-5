package com.example.course_booking;


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

}
