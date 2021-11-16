package com.example.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EventPopupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private LinearLayout layoutMeeting;

    private LinearLayout layoutAssignment;

    private TextView tvDate;

    private EditText editTitle;

    private EditText editPlace;

    private EditText editContents;

    // Spinner type
    private Spinner spinnerType;

    private ArrayList<String> typeSpinnerItems = new ArrayList<>();

    private ArrayAdapter typeArrayAdapter;
    // Spinner type end

    // Spinner year
    private Spinner spinnerYear;

    private ArrayList<String> yearSpinnerItems = new ArrayList<>();

    private ArrayAdapter yearArrayAdapter;
    // Spinner year end

    // Spinner month
    private Spinner spinnerMonth;

    private ArrayList<String> monthSpinnerItems = new ArrayList<>();

    private ArrayAdapter monthArrayAdapter;
    // Spinner month end

    // Spinner day
    private Spinner spinnerDay;

    private ArrayList<String> daySpinnerItems = new ArrayList<>();

    private ArrayAdapter dayArrayAdapter;
    // Spinner day end

    // Spinner hour
    private Spinner spinnerHour;

    private ArrayList<String> hourSpinnerItems = new ArrayList<>();

    private ArrayAdapter hourArrayAdapter;
    // Spinner hour end

    // Spinner minute
    private Spinner spinnerMinute;

    private ArrayList<String> minuteSpinnerItems = new ArrayList<>();

    private ArrayAdapter minuteArrayAdapter;
    // Spinner minute end

    private Button btnOK;

    private Button btnCancel;

    private int selectedYear;

    private int selectedMonth;

    private int selectedDay;

    private boolean isUpdate;

    private String eventId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_popup);

        layoutMeeting = findViewById(R.id.layout_meeting);
        layoutAssignment = findViewById(R.id.layout_assignment);

        tvDate = findViewById(R.id.text_date);
        editTitle = findViewById(R.id.edit_title);
        editPlace = findViewById(R.id.edit_meeting_place);
        editContents = findViewById(R.id.edit_contents);

        spinnerType = findViewById(R.id.spinner_type);
        spinnerYear = findViewById(R.id.spinner_year);
        spinnerMonth = findViewById(R.id.spinner_month);
        spinnerDay = findViewById(R.id.spinner_day);
        spinnerHour = findViewById(R.id.spinner_hour);
        spinnerMinute = findViewById(R.id.spinner_minute);

        btnOK = findViewById(R.id.btn_ok);
        btnCancel = findViewById(R.id.btn_cancel);

        Intent intent = getIntent();
        tvDate.setText(intent.getStringExtra("date"));

        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        // initially layoutAssignment is gone state
        layoutAssignment.setVisibility(View.GONE);

        spinnerType.setOnItemSelectedListener(this);

        initializeSpinners();

        selectedYear = intent.getIntExtra("year", 2019);
        selectedMonth = intent.getIntExtra("month", 1);
        selectedDay = intent.getIntExtra("day", 1);

        if (intent.getStringExtra("title") != null) { // existing

            isUpdate = true;
            //spinnerType.setEnabled(false);
            editTitle.setText(intent.getStringExtra("title"));
            editContents.setText(intent.getStringExtra("contents"));
            eventId = intent.getStringExtra("eventId");

            String type = intent.getStringExtra("type");
            if (type.equals("0")) { // assignment

                spinnerType.setSelection(1);

                int endYear = intent.getIntExtra("endYear", 0);
                int endMonth = intent.getIntExtra("endMonth", 0);
                int endDay = intent.getIntExtra("endDay", 0);

                spinnerYear.setSelection(endYear - 2019);
                spinnerMonth.setSelection(endMonth - 1);
                spinnerDay.setSelection(endDay - 1);

                layoutMeeting.setVisibility(View.GONE);
                layoutAssignment.setVisibility(View.VISIBLE);
                editPlace.setVisibility(View.GONE);
            } else if (type.equals("1")) { // meeting

                spinnerType.setSelection(0);

                int hour = intent.getIntExtra("hour", 0);
                int minute = intent.getIntExtra("minute", 0);

                spinnerHour.setSelection(hour - 1);
                spinnerMinute.setSelection(minute  / 15); // 15분 단위

                editPlace.setText(intent.getStringExtra("meetingPlace"));

                layoutMeeting.setVisibility(View.VISIBLE);
                layoutAssignment.setVisibility(View.GONE);
                editPlace.setVisibility(View.VISIBLE);
            }
        } else { // new

            spinnerYear.setSelection(selectedYear - 2019);
            spinnerMonth.setSelection(selectedMonth - 1);
            spinnerDay.setSelection(selectedDay - 1);
        }
    }

    private void initializeSpinners() {

        typeSpinnerItems.clear();
        typeSpinnerItems.add("미팅");
        typeSpinnerItems.add("과제");
        typeArrayAdapter = new ArrayAdapter<>(EventPopupActivity.this, android.R.layout.simple_spinner_dropdown_item, typeSpinnerItems);
        spinnerType.setAdapter(typeArrayAdapter);

        yearSpinnerItems.clear();
        yearSpinnerItems.add("2019");
        yearSpinnerItems.add("2020");
        yearSpinnerItems.add("2021");
        yearSpinnerItems.add("2022");
        yearSpinnerItems.add("2023");
        yearSpinnerItems.add("2024");
        yearSpinnerItems.add("2025");
        yearSpinnerItems.add("2026");
        yearSpinnerItems.add("2027");
        yearSpinnerItems.add("2028");
        yearArrayAdapter = new ArrayAdapter<>(EventPopupActivity.this, android.R.layout.simple_spinner_dropdown_item, yearSpinnerItems);
        spinnerYear.setAdapter(yearArrayAdapter);

        monthSpinnerItems.clear();
        monthSpinnerItems.add("1");
        monthSpinnerItems.add("2");
        monthSpinnerItems.add("3");
        monthSpinnerItems.add("4");
        monthSpinnerItems.add("5");
        monthSpinnerItems.add("6");
        monthSpinnerItems.add("7");
        monthSpinnerItems.add("8");
        monthSpinnerItems.add("9");
        monthSpinnerItems.add("10");
        monthSpinnerItems.add("11");
        monthSpinnerItems.add("12");
        monthArrayAdapter = new ArrayAdapter<>(EventPopupActivity.this, android.R.layout.simple_spinner_dropdown_item, monthSpinnerItems);
        spinnerMonth.setAdapter(monthArrayAdapter);

        daySpinnerItems.clear();
        daySpinnerItems.add("1");
        daySpinnerItems.add("2");
        daySpinnerItems.add("3");
        daySpinnerItems.add("4");
        daySpinnerItems.add("5");
        daySpinnerItems.add("6");
        daySpinnerItems.add("7");
        daySpinnerItems.add("8");
        daySpinnerItems.add("9");
        daySpinnerItems.add("10");
        daySpinnerItems.add("11");
        daySpinnerItems.add("12");
        daySpinnerItems.add("13");
        daySpinnerItems.add("14");
        daySpinnerItems.add("15");
        daySpinnerItems.add("16");
        daySpinnerItems.add("17");
        daySpinnerItems.add("18");
        daySpinnerItems.add("19");
        daySpinnerItems.add("20");
        daySpinnerItems.add("21");
        daySpinnerItems.add("22");
        daySpinnerItems.add("23");
        daySpinnerItems.add("24");
        daySpinnerItems.add("25");
        daySpinnerItems.add("26");
        daySpinnerItems.add("27");
        daySpinnerItems.add("28");
        daySpinnerItems.add("29");
        daySpinnerItems.add("30");
        daySpinnerItems.add("31");
        dayArrayAdapter = new ArrayAdapter<>(EventPopupActivity.this, android.R.layout.simple_spinner_dropdown_item, daySpinnerItems);
        spinnerDay.setAdapter(dayArrayAdapter);

        hourSpinnerItems.clear();
        hourSpinnerItems.add("1");
        hourSpinnerItems.add("2");
        hourSpinnerItems.add("3");
        hourSpinnerItems.add("4");
        hourSpinnerItems.add("5");
        hourSpinnerItems.add("6");
        hourSpinnerItems.add("7");
        hourSpinnerItems.add("8");
        hourSpinnerItems.add("9");
        hourSpinnerItems.add("10");
        hourSpinnerItems.add("11");
        hourSpinnerItems.add("12");
        hourSpinnerItems.add("13");
        hourSpinnerItems.add("14");
        hourSpinnerItems.add("15");
        hourSpinnerItems.add("16");
        hourSpinnerItems.add("17");
        hourSpinnerItems.add("18");
        hourSpinnerItems.add("19");
        hourSpinnerItems.add("20");
        hourSpinnerItems.add("21");
        hourSpinnerItems.add("22");
        hourSpinnerItems.add("23");
        hourSpinnerItems.add("24");
        hourArrayAdapter = new ArrayAdapter<>(EventPopupActivity.this, android.R.layout.simple_spinner_dropdown_item, hourSpinnerItems);
        spinnerHour.setAdapter(hourArrayAdapter);

        minuteSpinnerItems.clear();
        minuteSpinnerItems.add("0");
        minuteSpinnerItems.add("15");
        minuteSpinnerItems.add("30");
        minuteSpinnerItems.add("45");
        minuteArrayAdapter = new ArrayAdapter<>(EventPopupActivity.this, android.R.layout.simple_spinner_dropdown_item, minuteSpinnerItems);
        spinnerMinute.setAdapter(minuteArrayAdapter);
    }

    @Override
    // Spiner onItemSelected listener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        int id = adapterView.getId();
        switch (id) {

            case R.id.spinner_type:

                int index = spinnerType.getSelectedItemPosition();
                if (index == 0) {

                    layoutMeeting.setVisibility(View.VISIBLE);
                    layoutAssignment.setVisibility(View.GONE);
                    editPlace.setVisibility(View.VISIBLE);
                } else {

                    layoutMeeting.setVisibility(View.GONE);
                    layoutAssignment.setVisibility(View.VISIBLE);
                    editPlace.setVisibility(View.GONE);
                }
                break;

            case R.id.spinner_year:
                break;
            case R.id.spinner_month:
                break;
            case R.id.spinner_day:
                break;
            case R.id.spinner_hour:
                break;
            case R.id.spinner_minute:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {

            case R.id.btn_ok:

                // TODO: SAVE

                Intent intent = new Intent();

                String title = editTitle.getText().toString();
                String contents = editContents.getText().toString();

                intent.putExtra("title", title);
                intent.putExtra("contents", contents);
                intent.putExtra("selectedYear", selectedYear);
                intent.putExtra("selectedMonth", selectedMonth);
                intent.putExtra("selectedDay", selectedDay);

                if (layoutAssignment.getVisibility() == View.VISIBLE) {

                    int year = Integer.parseInt(spinnerYear.getSelectedItem().toString());
                    int month = Integer.parseInt(spinnerMonth.getSelectedItem().toString());
                    int day = Integer.parseInt(spinnerDay.getSelectedItem().toString());

                    intent.putExtra("type", "0"); // 0 == assignment
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    intent.putExtra("day", day);
                } else if (layoutMeeting.getVisibility() == View.VISIBLE) {

                    int hour = Integer.parseInt(spinnerHour.getSelectedItem().toString());
                    int minute = Integer.parseInt(spinnerMinute.getSelectedItem().toString());
                    String meetingPlace = editPlace.getText().toString();

                    intent.putExtra("type", "1"); // 1 == meeting
                    intent.putExtra("hour", hour);
                    intent.putExtra("minute", minute);
                    intent.putExtra("meetingPlace", meetingPlace);
                }

                if (isUpdate == true) {

                    intent.putExtra("update", isUpdate);
                    intent.putExtra("eventId", eventId);
                }

                setResult(-1/*RESULT_OK*/, intent);
                this.finish();

                break;

            case R.id.btn_cancel:

                this.finish();

                break;
        }
    }
}
