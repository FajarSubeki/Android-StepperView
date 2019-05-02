package com.example.stepper;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.stepper.Adapter.StepperAdapter;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;

public class MainActivity extends AppCompatActivity {

    public static StepperLayout stepperLayout;


    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] titles = new String[]{"Name","Password","Region","Email","Agree"};
        Step[] steps = new Step[]{
                new BiodataFragment(),
                new PasswordFragment(),
                new RegionFragment(),
                new EmailFragment(),
                new AgreeFragment()
        };

        StepperAdapter stepperAdapter = new StepperAdapter(getSupportFragmentManager(), this, titles,steps);

        stepperLayout = findViewById(R.id.stepperLayout);
        stepperLayout.setAdapter(stepperAdapter);

    }

}
