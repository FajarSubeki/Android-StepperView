package com.example.stepper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stepper.Model.User;
import com.example.stepper.Network.LoginServices;
import com.example.stepper.Util.PrefUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailtext, passwordtext;
    private Button login;
    private LoginServices loginServices;
    User user;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (isSessionLogin()){
            HomeActivity.start(this);
            LoginActivity.this.finish();
        }

        emailtext = findViewById(R.id.etEmail);
        passwordtext = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAct();
            }
        });
    }

    void loginAct(){
        String email = emailtext.getText().toString();
        String password = passwordtext.getText().toString();

        if (TextUtils.isEmpty(email)){
            emailtext.setError("Email cannot be empty !");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordtext.setError("Password cannot be empty !");
            return;
        }

        loginServices = new LoginServices(this);
        loginServices.doLogin(email, password, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                user = (User) response.body();

                if(user != null) {
                    if(!user.isError()) {
                        PrefUtil.putUser(LoginActivity.this, PrefUtil.USER_SESSION, user);
                        HomeActivity.start(LoginActivity.this);
                        LoginActivity.this.finish();
                    }

                    Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Gagal !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean isSessionLogin(){
        return PrefUtil.getUser(this, PrefUtil.USER_SESSION) != null;
    }

}
