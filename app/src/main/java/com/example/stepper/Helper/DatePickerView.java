package com.example.stepper.Helper;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerView extends AppCompatEditText implements DatePickerDialog.OnDateSetListener {

    private Date date;

    public DatePickerView(Context context) {
        super(context);
        setAttributes();
    }

    public DatePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes();
    }

    public DatePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();

        setDate(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date != null) {
            this.date = date;
            SimpleDateFormat newformat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = newformat.format(date);
            setText(formattedDate);
        } else {

            setText("");
        }
    }

    public Calendar getModifiedDate() {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar;
    }

    private void setAttributes(){
        setHint("Select Date");
        setGravity(Gravity.LEFT | Gravity.CENTER);
        setFocusable(false);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                if (date != null) {
                    calendar.setTime(date);
                }
                DatePickerDialog datePicker = new DatePickerDialog(
                        DatePickerView.this.getContext(), DatePickerView.this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.setCancelable(false);

                // datePicker.setCanceledOnTouchOutside(true);
                datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_NEGATIVE) {
                            dialog.dismiss();

                        }
                    }
                });


                datePicker.show();
            }
        });
    }
}
