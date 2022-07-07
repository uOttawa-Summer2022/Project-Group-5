package com.example.course_booking;

public class Validator {

    String usernameR = "[A-Za-z]+";
    String crsCodeR = "[A-Z]{3}[0-9]{4}";


    public Validator() {}

    public boolean validUsername(String username) {
        if (username.matches(usernameR)) { return true; }
        else { return false; }
    }

    public boolean validCrsCode(String crsCode) {
        if (crsCode.matches(crsCodeR)) { return true; }
        else { return false; }
    }

    public boolean validSession(int startHour , int startMin, int endHour, int endMin){

        if(startHour < 0 || startHour > 23){return false;}

        if(startMin < 0 || startMin > 59){return false;}

        if(endHour < 0 || endHour > 23){return false;}

        if(endMin < 0 || endMin > 59){return false;}

        else { return true; }
    }

}
