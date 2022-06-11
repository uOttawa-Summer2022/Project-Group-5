package com.example.course_booking;

public class CourseModel {
    private String crsName;
    private String crsCode;


    //constructors
    public CourseModel(String crsName, String crsCode) {
        this.crsName = crsName;
        this.crsCode = crsCode;
    }


    //getters and setters
    public String getCrsName() {
        return crsName;
    }

    public void setCrsName(String crsName) {
        this.crsName = crsName;
    }

    public String getCrsCode() {
        return crsCode;
    }

    public void setCrsCode(String crsCode) {
        this.crsCode = crsCode;
    }

    @Override
    public String toString() {
        return "CourseModel{" +
                "crsName='" + crsName + '\'' +
                ", crsCode='" + crsCode + '\'' +
                '}';
    }
}
