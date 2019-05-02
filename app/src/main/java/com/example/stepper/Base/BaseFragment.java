package com.example.stepper.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

public class BaseFragment extends Fragment {

    public View view;
    public static String firsname;
    public static String lastname;
    public static String dateofbirth;
    public static String password;
    public static String nationality;
    public static String address;
    public static String phoneno;
    public static String email;
    ProgressDialog progressDialog;

    public void showLoading(Context context)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Tunggu Sebentar..");


        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissLoading()
    {
        progressDialog.dismiss();
    }

}
