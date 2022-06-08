package com.example.course_booking;

public class UserModel {
    public String username;
    public String password;

    public UserModel(){

    }

    public UserModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){ return username;}
    public void setUsername(){this.username = username;}
    public String getPassword(){ return password;}
    public void setPassword(){this.password = password;}


}
