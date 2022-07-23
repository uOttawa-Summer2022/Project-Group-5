package com.example.course_booking;

import java.util.ArrayList;

public class CourseModel {
    private String crsName;
    private String crsCode;
    private String crsDescription;
    private int crsCapacity;
    private ArrayList<Session> crsSessionList;
    private ArrayList<String> studentsList;
    private String crsInstructor;


    //constructors
    public CourseModel(String crsName, String crsCode) {
        this.crsName = crsName;
        this.crsCode = crsCode;
        this.crsDescription = "";
        this.crsCapacity = 0;
        this.crsInstructor = "";
        this.crsSessionList = new ArrayList<Session>();
        this.studentsList = new ArrayList<String>();
    }

    public CourseModel() {
        this.crsName = "";
        this.crsCode = "";
        this.crsDescription = "";
        this.crsCapacity = 0;
        this.crsInstructor = "";
        this.crsSessionList = new ArrayList<Session>();
        this.studentsList = new ArrayList<String>();
    }


    //getters and setters
    public String getCrsName() {
        return crsName;
    }

    public void setCrsName(String crsName) {
        this.crsName = crsName;
    }

    public String getCrsInstructor() {
        return crsInstructor;
    }

    public void setCrsInstructor(String name) {
        this.crsInstructor = name;
    }

    public String getCrsCode() {
        return crsCode;
    }

    public void setCrsCode(String crsCode) {
        this.crsCode = crsCode;
    }

    public String getCrsDescription() {
        return crsDescription;
    }

    public void setCrsDescription(String crsDescription) {
        this.crsDescription = crsDescription;
    }

    public int getCrsCapacity() {return crsCapacity;}

    public void setCrsCapacity(int capacity) {
        this.crsCapacity = capacity;
    }

    public void setCrsSessionList(ArrayList<Session> crsSessionList) {
        this.crsSessionList = crsSessionList;
    }

    public void setStudentsList(ArrayList<String> studentsList) {
        this.studentsList = studentsList;
    }

    public ArrayList<String> getStudentsList() {
        return studentsList;
    }

    public ArrayList<Session> getcrsSessionList() {return crsSessionList;}

    @Override
    public String toString() {
        String sessList = "";
        for (Session session: crsSessionList){
            sessList += session.toString();
        }
        return "CourseModel{" +
                "crsName='" + crsName + '\'' +
                ", crsCode='" + crsCode + '\'' +
                ", crsDescription='" + crsDescription + '\'' +
                ", crsCapacity='" + crsCapacity + '\'' +
                ", crsInstructor='" + crsInstructor + '\'' +
                ", crsSessionList='" + sessList + '\'' +
                '}';
    }
}
