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

}
