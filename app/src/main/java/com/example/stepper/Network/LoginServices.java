package com.example.stepper.Network;

import android.content.Context;

import com.example.stepper.Interface.LoginInterface;

import retrofit2.Callback;

public class LoginServices {

    private LoginInterface loginInterface;

    public LoginServices(Context context){
        loginInterface = RetroBuilder.bulder(context)
                .create(LoginInterface.class);
    }

    public void doLogin(String Email, String Password, Callback callback){
        loginInterface.login(Email, Password).enqueue(callback);
    }

}
