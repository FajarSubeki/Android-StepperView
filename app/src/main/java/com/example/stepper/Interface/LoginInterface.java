package com.example.stepper.Interface;

import com.example.stepper.Model.User;
import com.example.stepper.Network.Config;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {

    @FormUrlEncoded
    @POST(Config.API_LOGIN)
    Call<User> login(
            @Field("Email") String Email,
            @Field("Password") String Password);

}
