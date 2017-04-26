package com.example.user.sqlitedemo;

/**
 * Created by ASUS on 4/26/2017.
 */

class Users {

    int id;
    String username;
    String password;
    String email;
    String gender;
    double height;
    double initialWeight;
    double goalWeight;
    double currentWeight;

    public Users(int id, String username, String password, String email, String gender, double height, double initialWeight) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.height = height;
        this.initialWeight = initialWeight;
    }

    public double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }
}
