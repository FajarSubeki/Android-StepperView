package com.example.stepper.Model;

public class User extends BaseResponse {

    private UserData data;

    public User() {
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
