package com.example.stepper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.stepper.Base.BaseFragment;
import com.example.stepper.Model.UserData;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class PasswordFragment extends Fragment implements BlockingStep {

    View view;
    Button btnNext;
    Toolbar toolbar;

    private EditText password;

    UserData userData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_password_fragment, container, false);

        toolbar = view.findViewById(R.id.toolbar_profile);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.stepperLayout.setCurrentStepPosition(0);
            }
        });

        init();
        nextClicked();

        return view;

    }

    private void init()
    {
        password = view.findViewById(R.id.tv_password);
        btnNext = view.findViewById(R.id.btnNextPassword);
    }

    private void nextClicked()
    {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    BaseFragment.password = password.getText().toString();
                    MainActivity.stepperLayout.setCurrentStepPosition(2);
                }
            }
        });
    }

    private boolean validate()
    {
        if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Field Harus Diisi");
        }else{
            return true;
        }
        return false;
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
