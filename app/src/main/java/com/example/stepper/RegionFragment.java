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
import com.hbb20.CountryCodePicker;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

public class RegionFragment extends Fragment implements BlockingStep {

    View view;
    Button btnNext;
    Toolbar toolbar;
    EditText address, phoneno;
    MaterialEditText nationality;
    CountryCodePicker ccp;
    CountryPicker picker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_region_fragment ,container, false);

        toolbar = view.findViewById(R.id.tb_region);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.stepperLayout.setCurrentStepPosition(1);
            }
        });

        init();
        nextClicked();

        return view;

    }

    private void init()
    {
        nationality = view.findViewById(R.id.tvNationally);
        address = view.findViewById(R.id.tvAddress);
        phoneno = view.findViewById(R.id.phoneText);
        btnNext = view.findViewById(R.id.btnNextRegion);

        ccp = view.findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phoneno);

        picker = CountryPicker.newInstance("Select Country");
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String s1, String s2, int i) {
                nationality = view.findViewById(R.id.tvNationally);
                nationality.setText(name);

                picker.dismiss();
            }
        });

        nationality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.show(getFragmentManager(), "COUNTRY_PICKER");
            }
        });
    }

    private void nextClicked()
    {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    BaseFragment.nationality = nationality.getText().toString();
                    BaseFragment.address = address.getText().toString();
                    BaseFragment.phoneno = phoneno.getText().toString();
                    MainActivity.stepperLayout.setCurrentStepPosition(3);
                }
            }
        });
    }

    private boolean validate()
    {
        if (TextUtils.isEmpty(nationality.getText().toString())){
            nationality.setError("Field Harus Diisi");
        }else if (TextUtils.isEmpty(address.getText().toString())){
            address.setError("Field Harus Diisi");
        }else if (TextUtils.isEmpty(phoneno.getText().toString())){
            phoneno.setError("Field Harus Diisi");
        }else{
            return true;
        }
        return false;
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
