package com.example.course_booking;

public class AdminModel extends UserModel{

    public AdminModel(String name, String password, Type accType) {
        super(name, password, accType);
    }

    //To avoid confusion, this part is hidden since it's not used.

    /*public void createCourse(String crsCode, String crsName){
        //CourseModel newCourse = new CourseModel(crsName, crsCode);
    }

    public void editCourse(String crsCode, String crsName){

    }

    public void deleteCourse(String crsCode, String crsName){

    }

    public void deleteAccount(String acc, Type accType){

    }*/

}
