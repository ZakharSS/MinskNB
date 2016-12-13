package com.iit.zakhar.minsknb.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog.OnDateSetListener;


public class DatePicker extends DialogFragment {

    private int myYear;
    private int myMonth;
    private int myDay;

    public static final String STARTDATEPICKER = "startDatePicker";
    public static final String ENDDATEPICKER = "endDatePicker";
    public static final String YEAR_ARG = "year";
    public static final String MONTH_ARG = "month";
    public static final String DAY_ARG = "day";

    OnDateSetListener onStartDateSet;
    OnDateSetListener onEndDateSet;

    public DatePicker() {
    }

    public void setStartDateCallBack(OnDateSetListener onDateSet) {
        onStartDateSet = onDateSet;
    }

    public void setEndDateCallBack(OnDateSetListener onDateSet) {
        onEndDateSet = onDateSet;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        myYear = args.getInt(YEAR_ARG);
        myMonth = args.getInt(MONTH_ARG);
        myDay = args.getInt(DAY_ARG);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (getTag().compareTo(STARTDATEPICKER) == 0) {
            return new DatePickerDialog(getActivity(), onStartDateSet, myYear, myMonth, myDay);
        }
        if (getTag().compareTo(ENDDATEPICKER) == 0) {
            return new DatePickerDialog(getActivity(), onEndDateSet, myYear, myMonth, myDay);
        }
        return null;
    }
}
