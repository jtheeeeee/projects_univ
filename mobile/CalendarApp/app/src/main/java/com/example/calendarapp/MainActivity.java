package com.example.calendarapp;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.calendarapp.adapter.ListAdapter;
import com.example.calendarapp.decorator.EventDecorator;
import com.example.calendarapp.decorator.OneDayDecorator;
import com.example.calendarapp.decorator.SaturdayDecorator;
import com.example.calendarapp.decorator.SundayDecorator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    MaterialCalendarView materialCalendarView;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();

    private static final String[] SCOPES = { CalendarScopes.CALENDAR };
    GoogleAccountCredential mCredential;

    private static final String PREF_ACCOUNT_NAME = "accountName";
    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;
    static final int CALENDAR_POPUP_ITEM = 1004;
    ProgressDialog mProgress;
    List<Event> items;

    /**
     * Google Calendar API ?????? ?????? ???????????? ??? AsyncTask??? ??????????????? ?????? ??????
     */
    private  int mID = 0;

    /**
     * Google Calendar API??? ???????????? ?????? ???????????? ?????? ????????? API ????????? ??????
     */
    private com.google.api.services.calendar.Calendar mService = null;

    // ????????? ????????? ?????? ?????? ????????? ???
    private ListView listView;

    private ListAdapter adapter = new ListAdapter();

    private Toolbar toolbar;

    private CalendarDay selectedDate;

    private String calendarID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialCalendarView = findViewById(R.id.calendarView);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(java.util.Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2010, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);

        // Google Calendar API ???????????? ???????????? ProgressDialog
        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Google Calendar API ?????? ????????????.");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getGoogleApiCredential();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                selectedDate = date;

                int year = date.getYear();
                int month = date.getMonth() + 1;
                int day = date.getDay();

                //materialCalendarView.clearSelection();

                java.util.Calendar calendar = java.util.Calendar.getInstance();

                if (items != null) {

                    adapter.clear();
                    for (Event event : items) {

                        calendar.setTimeInMillis(event.getStart().getDateTime().getValue());
                        CalendarDay eventDay = CalendarDay.from(calendar);
                        if (eventDay.getYear() == year && eventDay.getMonth() == (month - 1) && eventDay.getDay() == day) {

                            adapter.addItem(event);
                        }
                    }

                    adapter.notifyDataSetChanged();
                }
            }
        });

        mID = 3;
        getResultsFromApi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.menu_search:

                if (selectedDate == null) {

                    Toast.makeText(this, "????????? ?????? ??? ?????????.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                int year = selectedDate.getYear();
                int month = selectedDate.getMonth() + 1;
                int day = selectedDate.getDay();

                String shot_Day = year + " ??? " + month + " ??? " + day + " ???";

                Intent intent = new Intent(MainActivity.this, EventPopupActivity.class);
                intent.putExtra("date", shot_Day);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);

                startActivityForResult(intent, CALENDAR_POPUP_ITEM);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private String getResultsFromApi() {

        if (!isGooglePlayServicesAvailable()) { // Google Play Services??? ????????? ??? ?????? ??????

            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) { // ????????? Google ????????? ???????????? ?????? ?????? ??????

            chooseAccount();
        } else if (!isDeviceOnline()) {    // ???????????? ????????? ??? ?????? ??????

            //Toast.makeText(this, "No network connection available.")
        } else {


            // Google Calendar API ??????
            new MakeRequestTask(this, mCredential).execute();
        }
        return null;
    }


    private String getResultsFromApiAddEvent(Intent data) {

        if (!isGooglePlayServicesAvailable()) { // Google Play Services??? ????????? ??? ?????? ??????

            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) { // ????????? Google ????????? ???????????? ?????? ?????? ??????

            chooseAccount();
        } else if (!isDeviceOnline()) {    // ???????????? ????????? ??? ?????? ??????

            //Toast.makeText(this, "No network connection available.")
        } else {

            // Google Calendar API ??????
            new AddEventTask(this, mCredential, data).execute();
        }
        return null;
    }

    /**
     * ??????????????? ??????????????? ?????? ????????? Google Play Services??? ???????????? ????????? ??????
     */
    private boolean isGooglePlayServicesAvailable() {

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();

        final int connectionStatusCode = apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }

    /*
     * Google Play Services ??????????????? ????????????????????? ???????????? ?????? ???????????? ????????????????????? ??????????????????
     * ??????????????? ?????????.
     */
    private void acquireGooglePlayServices() {

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        final int connectionStatusCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {

            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
        }
    }



    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQUEST_GOOGLE_PLAY_SERVICES:

                if (resultCode != RESULT_OK) {

                } else {

                    getResultsFromApi();
                }

                break;

            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null && data.getExtras() != null) {
                    String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                        mCredential.setSelectedAccountName(accountName);
                        getResultsFromApi();
                    }
                }

                break;

            case REQUEST_AUTHORIZATION:

                if (resultCode == RESULT_OK) {
                    getResultsFromApi();
                }

                break;

            case CALENDAR_POPUP_ITEM:

                if (resultCode == RESULT_OK && data != null) {

                    getResultsFromApiAddEvent(data);
                }

                break;
        }
    }


    private boolean isDeviceOnline() {

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

    /*
     * Google Calendar API??? ?????? ??????( credentials ) ??? ????????? ?????? ????????? ????????????.
     */
    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
    private void chooseAccount() {

        // GET_ACCOUNTS ????????? ????????? ?????????
        if (EasyPermissions.hasPermissions(this, Manifest.permission.GET_ACCOUNTS)) {

            // SharedPreferences?????? ????????? Google ?????? ????????? ????????????.
            String accountName = getPreferences(Context.MODE_PRIVATE)
                    .getString(PREF_ACCOUNT_NAME, null);
            if (accountName != null) {

                // ????????? ?????? ?????? ???????????? ????????????.
                mCredential.setSelectedAccountName(accountName);
                getResultsFromApi();
            } else {

                // ???????????? ?????? ????????? ????????? ??? ?????? ?????????????????? ????????????.
                startActivityForResult(
                        mCredential.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER);
            }

            // GET_ACCOUNTS ????????? ????????? ?????? ?????????
        } else {

            // ??????????????? GET_ACCOUNTS ????????? ???????????? ?????????????????? ????????????.(????????? ?????? ?????????)
            EasyPermissions.requestPermissions(
                    (Activity)this,
                    "This app needs to access your Google account (via Contacts).",
                    REQUEST_PERMISSION_GET_ACCOUNTS,
                    Manifest.permission.GET_ACCOUNTS);
        }
    }

    private void getGoogleApiCredential() {

        // Google Calendar API ???????????? ?????? ????????? ?????? ?????????( ?????? ?????? credentials, ????????? ?????? )
        // OAuth 2.0??? ???????????? ?????? ?????? ?????? ??? ???????????? ?????? ??????
        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(),
                Arrays.asList(SCOPES)
        ).setBackOff(new ExponentialBackOff()); // I/O ?????? ????????? ???????????? ????????? ?????? ??????
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Event event = (Event) adapter.getItem(i);

        int year = selectedDate.getYear();
        int month = selectedDate.getMonth() + 1;
        int day = selectedDate.getDay();

        //materialCalendarView.clearSelection();

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        calendar.setTimeInMillis(event.getStart().getDateTime().getValue());
        CalendarDay eventDay = CalendarDay.from(calendar);

        String shot_Day = year + " ??? " + month + " ??? " + day + " ???";

        Intent intent = new Intent(MainActivity.this, EventPopupActivity.class);
        intent.putExtra("date", shot_Day);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);

        String[] item = event.getSummary().split("/");
        if (item != null && item.length == 2) {

            intent.putExtra("type", item[0]);
            intent.putExtra("title", item[1]);
            intent.putExtra("contents", event.getDescription());
            intent.putExtra("eventId", event.getId());

            calendar.setTimeInMillis(event.getEnd().getDateTime().getValue());
            CalendarDay eventEndDay = CalendarDay.from(calendar);

            if (item[0].equals("0")) { // assignment

                intent.putExtra("endYear", eventEndDay.getYear());
                intent.putExtra("endMonth", eventEndDay.getMonth() + 1);
                intent.putExtra("endDay", eventEndDay.getDay());
            } else if (item[0].equals("1")) { // meeting

                intent.putExtra("hour", calendar.get(java.util.Calendar.HOUR));
                intent.putExtra("minute", calendar.get(java.util.Calendar.MINUTE));
                intent.putExtra("meetingPlace", event.getLocation());
            }
        }

        startActivityForResult(intent, CALENDAR_POPUP_ITEM);
    }

    private class SetCalendarEvent extends AsyncTask<Void, Void, List<CalendarDay>> {

        List<Event> items; // ????????? ????????? ????????? ?????????
        ArrayList<CalendarDay> datesMeeting;
        ArrayList<CalendarDay> datesAssignment;

        SetCalendarEvent(List<Event> items){
            this.items = items;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            java.util.Calendar calendar = java.util.Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();
            datesMeeting = new ArrayList<>();
            datesAssignment = new ArrayList<>();

            for(int i = 0 ; i < items.size() ; i ++){

                Event event = items.get(i);
                try {

                    calendar.setTimeInMillis(event.getStart().getDateTime().getValue());
                    CalendarDay day = CalendarDay.from(calendar);

                    dates.add(day);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            //materialCalendarView.addDecorators(new EventDecorator(Color.GREEN, datesMeeting,MainActivity.this),
            //        (new EventDecorator(Color.BLUE, datesAssignment,MainActivity.this)));
            materialCalendarView.addDecorator(new EventDecorator(Color.MAGENTA, calendarDays,MainActivity.this));
        }
    }

    /*
     * ????????? ????????? ???????????? ????????? ID??? ??????
     */
    private String getCalendarID(String calendarTitle) {

        String id = null;

        // Iterate through entries in calendar list
        String pageToken = null;
        do {
            CalendarList calendarList = null;
            try {
                calendarList = mService.calendarList().list().setPageToken(pageToken).execute();
            } catch (UserRecoverableAuthIOException e) {
                startActivityForResult(e.getIntent(), REQUEST_AUTHORIZATION);
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            List<CalendarListEntry> items = calendarList.getItems();
            for (CalendarListEntry calendarListEntry : items) {

                if ( calendarListEntry.getSummary().toString().equals(calendarTitle)) {

                    id = calendarListEntry.getId().toString();
                }
            }
            pageToken = calendarList.getNextPageToken();
        } while (pageToken != null);

        return id;
    }

    private class MakeRequestTask extends AsyncTask<Void, Void, String> {

        private Exception mLastError = null;
        private MainActivity mActivity;
        List<String> eventStrings = new ArrayList<String>();

        public MakeRequestTask(MainActivity activity, GoogleAccountCredential credential) {

            mActivity = activity;

            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

            mService = new com.google.api.services.calendar.Calendar
                    .Builder(transport, jsonFactory, credential)
                    .setApplicationName("com.example.calendarapp")
                    .build();
        }

        @Override
        protected void onPreExecute() {
            // mStatusText.setText("");
            mProgress.show();
        }

        /*
         * ????????????????????? Google Calendar API ?????? ??????
         */
        @Override
        protected String doInBackground(Void... params) {

            try {

                calendarID = getCalendarID("CalendarTitle");
                if ( calendarID == null ){

                    createCalendar();
                    calendarID = getCalendarID("CalendarTitle");
                    if (calendarID == null) {

                        return "error - calendar not created";
                    }

                }

                return getEvent();
            } catch (Exception e) {
                mLastError = e;
                cancel(true);
            }

            return null;
        }

        /*
         * ???????????? ?????? Google ????????? ??? ???????????? ????????????.
         */
        private String createCalendar() throws IOException {

            String ids = getCalendarID("CalendarTitle");

            if ( ids != null ){

                return "?????? ???????????? ???????????? ????????????. ";
            }

            // ????????? ????????? ??????
            com.google.api.services.calendar.model.Calendar calendar = new Calendar();

            // ???????????? ?????? ??????
            calendar.setSummary("CalendarTitle");


            // ???????????? ????????? ??????
            calendar.setTimeZone("Asia/Seoul");

            // ?????? ???????????? ?????? ?????? ???????????? ??????
            Calendar createdCalendar = mService.calendars().insert(calendar).execute();

            // ????????? ???????????? ID??? ?????????.
            String calendarId = createdCalendar.getId();



            CalendarListEntry calendarListEntry = mService.calendarList().get(calendarId).execute();


            calendarListEntry.setBackgroundColor("#0000ff");

            // ????????? ????????? ?????? ???????????? ??????
            CalendarListEntry updatedCalendarListEntry =
                    mService.calendarList()
                            .update(calendarListEntry.getId(), calendarListEntry)
                            .setColorRgbFormat(true)
                            .execute();

            // ?????? ????????? ???????????? ID??? ??????
            return "???????????? ?????????????????????.";
        }


        private String getEvent() throws IOException {

            Events events = mService.events().list(calendarID)//"primary")
                    .setMaxResults(1000)
                    //.setTimeMin(now)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();

            items = events.getItems();

            for (Event event : items) {

                DateTime start = event.getStart().getDateTime();
                if (start == null) {


                    start = event.getStart().getDate();
                }

                //mService.events().delete(calendarID, event.getId()).execute();
                eventStrings.add(String.format("%s \n (%s)", event.getSummary(), start));
            }

            return eventStrings.size() + "?????? ???????????? ??????????????????.";
        }

        @Override
        protected void onPostExecute(String output) {

            new SetCalendarEvent(items).executeOnExecutor(Executors.newSingleThreadExecutor());
            mProgress.hide();
        }

        @Override
        protected void onCancelled() {
            mProgress.hide();
            if (mLastError != null) {
                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                    showGooglePlayServicesAvailabilityErrorDialog(
                            ((GooglePlayServicesAvailabilityIOException) mLastError)
                                    .getConnectionStatusCode());
                } else if (mLastError instanceof UserRecoverableAuthIOException) {
                    startActivityForResult(
                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
                            MainActivity.REQUEST_AUTHORIZATION);
                } else {

                }
            } else {

            }
        }
    }

    private class AddEventTask extends AsyncTask<Void, Void, String> {

        private Exception mLastError = null;
        private MainActivity mActivity;
        private Intent data;
        List<String> eventStrings = new ArrayList<String>();

        public AddEventTask(MainActivity activity, GoogleAccountCredential credential, Intent data) {

            mActivity = activity;

            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

            mService = new com.google.api.services.calendar.Calendar
                    .Builder(transport, jsonFactory, credential)
                    .setApplicationName("com.example.calendarapp")
                    .build();

            this.data = data;
        }

        @Override
        protected void onPreExecute() {
            // mStatusText.setText("");
            mProgress.show();
        }

        /*
         * ????????????????????? Google Calendar API ?????? ??????
         */
        @Override
        protected String doInBackground(Void... params) {

            try {
                return addEvent();

            } catch (Exception e) {
                mLastError = e;
                cancel(true);
            }

            return null;
        }

        private String addEvent() {

            String type = data.getStringExtra("type");
            String title = data.getStringExtra("title");
            String contents = data.getStringExtra("contents");

            int year = data.getIntExtra("selectedYear", 0);
            int month = data.getIntExtra("selectedMonth", 0);
            int day = data.getIntExtra("selectedDay", 0);

            boolean isUpdate = data.getBooleanExtra("update", false);
            String eventId = data.getStringExtra("eventId");

            if (type.equals("0")) { // Assignment

                int endYear = data.getIntExtra("year", 0);
                int endMonth = data.getIntExtra("month", 0);
                int endDay = data.getIntExtra("day", 0);

                try {
                    addEventAssignment(title, contents, type, year, month, day, endYear, endMonth, endDay, isUpdate, eventId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (type.equals("1")) { // Meeting

                int hour = data.getIntExtra("hour", 0);
                int minute = data.getIntExtra("minute", 0);
                String meetingPlace = data.getStringExtra("meetingPlace");

                try {
                    addEventMeeting(title, contents, meetingPlace, type, year, month, day, hour, minute, isUpdate, eventId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        private String addEventMeeting(String title, String contents, String location, String type, int year, int month, int day,
                                       int hour, int minute, boolean isUpdate, String eventId) throws IOException {

            Event event = new Event()
                    .setSummary(type + "/" + title)
                    .setLocation(location)
                    .setDescription(contents);

            java.util.Calendar calander;

            calander = java.util.Calendar.getInstance();

            calander.set(java.util.Calendar.YEAR, year);
            calander.set(java.util.Calendar.MONTH, month - 1); // month ??? 0 ??? 1?????????
            calander.set(java.util.Calendar.DAY_OF_MONTH, day);
            calander.set(java.util.Calendar.HOUR, hour);
            calander.set(java.util.Calendar.MINUTE, minute);
            calander.set(java.util.Calendar.SECOND, 0);

            SimpleDateFormat simpledateformat;
            simpledateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+09:00", Locale.KOREA);
            //simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
            String datetime = simpledateformat.format(calander.getTime());

            DateTime endDateTime = new  DateTime(datetime);
            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone("Asia/Seoul");
            event.setStart(end); // must set start time
            event.setEnd(end);

            try {

                if (isUpdate == false) {

                    event = mService.events().insert(calendarID, event).execute();
                } else {

                    event = mService.events().update(calendarID, eventId, event).execute();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception", "Exception : " + e.toString());
            }
            System.out.printf("Event created: %s\n", event.getHtmlLink());
            Log.e("Event", "created : " + event.getHtmlLink());
            String eventStrings = "created : " + event.getHtmlLink();
            return eventStrings;
        }

        private String addEventAssignment(String title, String contents, String type, int startYear, int startMonth, int startDay,
                                          int endYear, int endMonth, int endDay, boolean isUpdate, String eventId) throws IOException {

            Event event = new Event()
                    .setSummary(type + "/" + title)
                    //.setLocation("?????????")
                    .setDescription(contents);

            java.util.Calendar calander;

            calander = java.util.Calendar.getInstance();

            calander.set(java.util.Calendar.YEAR, startYear);
            calander.set(java.util.Calendar.MONTH, startMonth - 1); // month ??? 0 ??? 1?????????
            calander.set(java.util.Calendar.DAY_OF_MONTH, startDay);
            calander.set(java.util.Calendar.HOUR, 0);
            calander.set(java.util.Calendar.MINUTE, 0);
            calander.set(java.util.Calendar.SECOND, 0);

            SimpleDateFormat simpledateformat;
            simpledateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+09:00", Locale.KOREA);
            //simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
            String datetime = simpledateformat.format(calander.getTime());

            DateTime startDateTime = new DateTime(datetime);
            EventDateTime start = new EventDateTime()
                    .setDateTime(startDateTime)
                    .setTimeZone("Asia/Seoul");
            event.setStart(start);

            Log.d("@@@", datetime);

            calander.set(java.util.Calendar.YEAR, endYear);
            calander.set(java.util.Calendar.MONTH, endMonth - 1); // month ??? 0 ??? 1?????????
            calander.set(java.util.Calendar.DAY_OF_MONTH, endDay);

            datetime = simpledateformat.format(calander.getTime());
            DateTime endDateTime = new DateTime(datetime);
            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone("Asia/Seoul");
            event.setEnd(end);

            try {

                if (isUpdate == false) {

                    event = mService.events().insert(calendarID, event).execute();
                } else {

                    event = mService.events().update(calendarID, eventId, event).execute();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception", "Exception : " + e.toString());
            }
            System.out.printf("Event created: %s\n", event.getHtmlLink());
            Log.e("Event", "created : " + event.getHtmlLink());
            String eventStrings = "created : " + event.getHtmlLink();
            return eventStrings;
        }

        @Override
        protected void onPostExecute(String output) {

            getResultsFromApi();
            mProgress.hide();
        }

        @Override
        protected void onCancelled() {
            mProgress.hide();
            if (mLastError != null) {
                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                    showGooglePlayServicesAvailabilityErrorDialog(
                            ((GooglePlayServicesAvailabilityIOException) mLastError)
                                    .getConnectionStatusCode());
                } else if (mLastError instanceof UserRecoverableAuthIOException) {
                    startActivityForResult(
                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
                            MainActivity.REQUEST_AUTHORIZATION);
                } else {

                }
            } else {

            }
        }
    }

    /*
     * ??????????????? ??????????????? Google Play Services??? ?????? ????????? ????????? ????????? ????????? ?????? ???????????? ????????????
     */
    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode
    ) {

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();

        Dialog dialog = apiAvailability.getErrorDialog(
                MainActivity.this,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES
        );
        dialog.show();
    }
}
