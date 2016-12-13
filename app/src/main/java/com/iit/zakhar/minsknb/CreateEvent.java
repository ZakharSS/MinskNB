package com.iit.zakhar.minsknb;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.DatePickerDialog.OnDateSetListener;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iit.zakhar.minsknb.fragments.DatePicker;
import com.iit.zakhar.minsknb.model.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class CreateEvent extends BaseActivity implements View.OnClickListener {

    public static final String STARTDATEPICKER = "startDatePicker";
    public static final String ENDDATEPICKER = "endDatePicker";
    public static final String YEAR_ARG = "year";
    public static final String MONTH_ARG = "month";
    public static final String DAY_ARG = "day";
    EditText etEventTitle;
    EditText etEventDescription;
    Spinner startDateSpinner;
    Spinner endDateSpinner;
    Spinner categorySpinner;
    ArrayAdapter<String> startDateAdapter;
    ArrayAdapter<String> endDateAdapter;
    ArrayAdapter<String> categoryAdapter;
    List<String> startDateChoice;
    List<String> endDateChoice;
    List<String> categoryChoice;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        startDateChoice = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dateChoice)));
        endDateChoice = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dateChoice)));
        categoryChoice = Arrays.asList(getResources().getStringArray(R.array.categoryChoice));

        startDateAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, startDateChoice);
        startDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        endDateAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, endDateChoice);
        endDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categoryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categoryChoice);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        findViewById(R.id.btnCreateEvent).setOnClickListener(this);
        findViewById(R.id.btnCancelCreation).setOnClickListener(this);
        etEventTitle = (EditText)findViewById(R.id.etEventTitle);
        etEventDescription = (EditText)findViewById(R.id.etEventDescription);

        startDateSpinner = (Spinner)findViewById(R.id.startDateSpinner);
        endDateSpinner = (Spinner)findViewById(R.id.endDateSpinner);
        categorySpinner = (Spinner)findViewById(R.id.chooseCategorySpinner);

        startDateSpinner.setAdapter(startDateAdapter);
        endDateSpinner.setAdapter(endDateAdapter);
        categorySpinner.setAdapter(categoryAdapter);

        startDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 2:
                        DatePicker startDateDialog = new DatePicker();
                        startDateDialog.setArguments(setCurrentData());
                        startDateDialog.setStartDateCallBack(onStartDateSet);
                        startDateDialog.show(getSupportFragmentManager(),STARTDATEPICKER);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        endDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 2:
                        DatePicker endDateDialog = new DatePicker();
                        endDateDialog.setArguments(setCurrentData());
                        endDateDialog.setEndDateCallBack(onEndDateSet);
                        endDateDialog.show(getSupportFragmentManager(),ENDDATEPICKER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    OnDateSetListener onStartDateSet = new OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String date = year+"."+(monthOfYear+1)+"."+dayOfMonth;
            startDateChoice.add(date);
            startDateAdapter.notifyDataSetChanged();
            startDateSpinner.setSelection(getIndex(startDateSpinner, date));
        }
    };

    OnDateSetListener onEndDateSet = new OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String date = year+"."+(monthOfYear+1)+"."+dayOfMonth;
            endDateChoice.add(date);
            endDateAdapter.notifyDataSetChanged();
            endDateSpinner.setSelection(getIndex(endDateSpinner, date));
        }
    };


    private int getIndex(Spinner spinner, String myString){
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

    private Bundle setCurrentData() {
        Calendar calendar = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt(YEAR_ARG, calendar.get(Calendar.YEAR));
        args.putInt(MONTH_ARG, calendar.get(Calendar.MONTH));
        args.putInt(DAY_ARG, calendar.get(Calendar.DAY_OF_MONTH));
        return args;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCancelCreation:
              finish();
                    break;
            case R.id.btnCreateEvent:
                break;
        }
    }

}
