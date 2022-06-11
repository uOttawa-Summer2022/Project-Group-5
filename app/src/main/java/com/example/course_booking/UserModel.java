package com.example.course_booking;

public abstract class UserModel {

    private String name;
    private String password;
    private Type accType;

    //constructors
    public UserModel(String name, String password, Type accType) {
        this.name = name;
        this.password = password;
        this.accType = accType;
    }


    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getAccType() {
        return accType;
    }

    public void setAccType(Type accType) {
        this.accType = accType;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", accType=" + accType +
                '}';
    }
}
