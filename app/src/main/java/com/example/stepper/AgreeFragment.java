package com.example.stepper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.stepper.Base.BaseFragment;
import com.example.stepper.Model.BaseResponse;
import com.example.stepper.Network.RegisterServices;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgreeFragment extends Fragment implements BlockingStep {

    Button btnNext;
    View view;
    Toolbar toolbar;
    RegisterServices registerServices;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_agree_fragment, container, false);

        toolbar = view.findViewById(R.id.tb_agree);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.stepperLayout.setCurrentStepPosition(3);
            }
        });

        init();

        return view;

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, AgreeFragment.class);
        context.startActivity(intent);
    }

    private void init()
    {
        btnNext = view.findViewById(R.id.btnNextAgree);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(BaseFragment.firsname, BaseFragment.lastname, BaseFragment.dateofbirth, BaseFragment.password, BaseFragment.nationality, BaseFragment.address, BaseFragment.phoneno, BaseFragment.email);
                clear();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clear()
    {
        BaseFragment.firsname = "";
        BaseFragment.lastname = "";
        BaseFragment.password = "";
        BaseFragment.nationality = "";
        BaseFragment.address = "";
        BaseFragment.phoneno = "";
        BaseFragment.email = "";
    }

    public void registerUser(String firstname, String lastname, String dateofbirth, String password, String nationality, String address, String phoneno, String email){
        registerServices = new RegisterServices(getActivity());
        registerServices.doRegister(firstname, lastname, dateofbirth, password, nationality, address, phoneno, email, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                BaseResponse baseResponse = (BaseResponse) response.body();

                if (baseResponse != null){
                    if (!baseResponse.isError()){
                        LoginActivity.start(context);
                        getActivity().finish();
                    }
                    Toast.makeText(getActivity(), "Register Successfull", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity(), "Register Successfull", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

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
