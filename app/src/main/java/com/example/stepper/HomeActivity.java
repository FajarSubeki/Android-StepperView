package com.example.stepper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stepper.Model.User;
import com.example.stepper.Util.PrefUtil;

public class HomeActivity extends AppCompatActivity {

    private TextView greeting;
    private TextView email;
    private Button btnLogout;

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        greeting =findViewById(R.id.greeting);
        email = findViewById(R.id.email);
        btnLogout = findViewById(R.id.btn_logout);

        User user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);

        greeting.setText(getResources().getString(R.string.greeting, user.getData().getFirstname()));
        email.setText(user.getData().getEmail());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutAct();

                LoginActivity.start(HomeActivity.this);
                HomeActivity.this.finish();
            }
        });


    }

    void logoutAct() {
        PrefUtil.clear(this);
    }
}
