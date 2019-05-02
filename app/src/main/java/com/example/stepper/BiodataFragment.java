package com.example.stepper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.stepper.Base.BaseFragment;
import com.example.stepper.Helper.DatePickerView;
import com.example.stepper.Model.UserData;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BiodataFragment extends Fragment implements BlockingStep{

    View view;
    Button btnNext;
    Toolbar toolbar;
    private DatePickerView tgl_birth;
    UserData userData;

    public BiodataFragment(){}

    EditText firstname, lastname, dateofbirth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_biodata_fragment, container, false);

        toolbar = view.findViewById(R.id.toolbar_profile);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        init();
        nextClicked();

        return view;

    }

    private void init()
    {
        firstname = view.findViewById(R.id.tv_firstname);
        lastname = view.findViewById(R.id.tv_lastname);
        dateofbirth = view.findViewById(R.id.dp_birth);
        btnNext = view.findViewById(R.id.btnNextBiodata);
    }

    private void nextClicked()
    {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    BaseFragment.firsname = firstname.getText().toString();
                    BaseFragment.lastname = lastname.getText().toString();
                    BaseFragment.dateofbirth = dateofbirth.getText().toString();
                    MainActivity.stepperLayout.setCurrentStepPosition(1);
                }
            }
        });
    }

    private boolean validate()
    {
        if (TextUtils.isEmpty(firstname.getText().toString())){
            firstname.setError("Field Harus Diisi");
        }else if (TextUtils.isEmpty(lastname.getText().toString())){
            lastname.setError("Field Harus Diisi");
        }else if (TextUtils.isEmpty(dateofbirth.getText().toString())) {
            dateofbirth.setError("Field Harus Diisi");
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
