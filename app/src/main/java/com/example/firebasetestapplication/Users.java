package com.example.firebasetestapplication;

public class Users {

    String firstName, lastName, age, userName, PassWord;

    public Users(){}

    public Users(String firstName, String lastName, String age, String userName, String passWord) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.userName = userName;
        this.PassWord = passWord;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
