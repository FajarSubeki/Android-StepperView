package com.example.stepper.Network;

import android.content.Context;

import com.example.stepper.Interface.RegisterInterface;

import retrofit2.Callback;

public class RegisterServices {

    private RegisterInterface registerInterface;

    public RegisterServices(Context context) {
        registerInterface = RetroBuilder.bulder(context)
                .create(RegisterInterface.class);
    }

    public void doRegister(String firstname, String lastname, String dateofbirth, String password, String nationality,
                           String address, String phoneno, String email, Callback callback) {
        registerInterface.register(firstname, lastname, dateofbirth, password, nationality, address, phoneno, email).enqueue(callback);
    }

}
