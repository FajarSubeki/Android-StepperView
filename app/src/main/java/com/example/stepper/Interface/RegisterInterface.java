package com.example.stepper.Interface;

import com.example.stepper.Model.BaseResponse;
import com.example.stepper.Network.Config;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterInterface {

    @FormUrlEncoded
    @POST(Config.API_REGISTER)
    Call<BaseResponse> register(
            @Field("FirstName") String firstname,
            @Field("LastName") String lastname,
            @Field("DateofBirth") String dateofbirth,
            @Field("Password") String password,
            @Field("Nationality") String nationality,
            @Field("Address") String address,
            @Field("PhoneNo") String phoneno,
            @Field("Email") String email);

}
