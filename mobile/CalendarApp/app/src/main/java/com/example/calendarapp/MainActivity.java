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
     * Google Calendar API 호출 관련 메커니즘 및 AsyncTask을 재사용하기 위해 사용
     */
    private  int mID = 0;

    /**
     * Google Calendar API에 접근하기 위해 사용되는 구글 캘린더 API 서비스 객체
     */
    private com.google.api.services.calendar.Calendar mService = null;

    // 이벤트 정보가 담겨 있는 리스트 뷰
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

        // Google Calendar API 호출중에 표시되는 ProgressDialog
        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Google Calendar API 호출 중입니다.");

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

                    Toast.makeText(this, "날짜를 선택 해 주세요.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                int year = selectedDate.getYear();
                int month = selectedDate.getMonth() + 1;
                int day = selectedDate.getDay();

                String shot_Day = year + " 년 " + month + " 월 " + day + " 일";

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

        if (!isGooglePlayServicesAvailable()) { // Google Play Services를 사용할 수 없는 경우

            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) { // 유효한 Google 계정이 선택되어 있지 않은 경우

            chooseAccount();
        } else if (!isDeviceOnline()) {    // 인터넷을 사용할 수 없는 경우

            //Toast.makeText(this, "No network connection available.")
        } else {


            // Google Calendar API 호출
            new MakeRequestTask(this, mCredential).execute();
        }
        return null;
    }


    private String getResultsFromApiAddEvent(Intent data) {

        if (!isGooglePlayServicesAvailable()) { // Google Play Services를 사용할 수 없는 경우

            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) { // 유효한 Google 계정이 선택되어 있지 않은 경우

            chooseAccount();
        } else if (!isDeviceOnline()) {    // 인터넷을 사용할 수 없는 경우

            //Toast.makeText(this, "No network connection available.")
        } else {

            // Google Calendar API 호출
            new AddEventTask(this, mCredential, data).execute();
        }
        return null;
    }

    /**
     * 안드로이드 디바이스에 최신 버전의 Google Play Services가 설치되어 있는지 확인
     */
    private boolean isGooglePlayServicesAvailable() {

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();

        final int connectionStatusCode = apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }

    /*
     * Google Play Services 업데이트로 해결가능하다면 사용자가 최신 버전으로 업데이트하도록 유도하기위해
     * 대화상자를 보여줌.
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
     * Google Calendar API의 자격 증명( credentials ) 에 사용할 구글 계정을 설정한다.
     */
    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
    private void chooseAccount() {

        // GET_ACCOUNTS 권한을 가지고 있다면
        if (EasyPermissions.hasPermissions(this, Manifest.permission.GET_ACCOUNTS)) {

            // SharedPreferences에서 저장된 Google 계정 이름을 가져온다.
            String accountName = getPreferences(Context.MODE_PRIVATE)
                    .getString(PREF_ACCOUNT_NAME, null);
            if (accountName != null) {

                // 선택된 구글 계정 이름으로 설정한다.
                mCredential.setSelectedAccountName(accountName);
                getResultsFromApi();
            } else {

                // 사용자가 구글 계정을 선택할 수 있는 다이얼로그를 보여준다.
                startActivityForResult(
                        mCredential.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER);
            }

            // GET_ACCOUNTS 권한을 가지고 있지 않다면
        } else {

            // 사용자에게 GET_ACCOUNTS 권한을 요구하는 다이얼로그를 보여준다.(주소록 권한 요청함)
            EasyPermissions.requestPermissions(
                    (Activity)this,
                    "This app needs to access your Google account (via Contacts).",
                    REQUEST_PERMISSION_GET_ACCOUNTS,
                    Manifest.permission.GET_ACCOUNTS);
        }
    }

    private void getGoogleApiCredential() {

        // Google Calendar API 사용하기 위해 필요한 인증 초기화( 자격 증명 credentials, 서비스 객체 )
        // OAuth 2.0를 사용하여 구글 계정 선택 및 인증하기 위한 준비
        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(),
                Arrays.asList(SCOPES)
        ).setBackOff(new ExponentialBackOff()); // I/O 예외 상황을 대비해서 백오프 정책 사용
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

        String shot_Day = year + " 년 " + month + " 월 " + day + " 일";

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

        List<Event> items; // 캘린더 이벤트 아이템 리스트
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
     * 캘린더 이름에 대응하는 캘린더 ID를 리턴
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
         * 백그라운드에서 Google Calendar API 호출 처리
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
         * 선택되어 있는 Google 계정에 새 캘린더를 추가한다.
         */
        private String createCalendar() throws IOException {

            String ids = getCalendarID("CalendarTitle");

            if ( ids != null ){

                return "이미 캘린더가 생성되어 있습니다. ";
            }

            // 새로운 캘린더 생성
            com.google.api.services.calendar.model.Calendar calendar = new Calendar();

            // 캘린더의 제목 설정
            calendar.setSummary("CalendarTitle");


            // 캘린더의 시간대 설정
            calendar.setTimeZone("Asia/Seoul");

            // 구글 캘린더에 새로 만든 캘린더를 추가
            Calendar createdCalendar = mService.calendars().insert(calendar).execute();

            // 추가한 캘린더의 ID를 가져옴.
            String calendarId = createdCalendar.getId();



            CalendarListEntry calendarListEntry = mService.calendarList().get(calendarId).execute();


            calendarListEntry.setBackgroundColor("#0000ff");

            // 변경한 내용을 구글 캘린더에 반영
            CalendarListEntry updatedCalendarListEntry =
                    mService.calendarList()
                            .update(calendarListEntry.getId(), calendarListEntry)
                            .setColorRgbFormat(true)
                            .execute();

            // 새로 추가한 캘린더의 ID를 리턴
            return "캘린더가 생성되었습니다.";
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

            return eventStrings.size() + "개의 데이터를 가져왔습니다.";
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
         * 백그라운드에서 Google Calendar API 호출 처리
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
            calander.set(java.util.Calendar.MONTH, month - 1); // month 는 0 이 1월이다
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
                    //.setLocation("서울시")
                    .setDescription(contents);

            java.util.Calendar calander;

            calander = java.util.Calendar.getInstance();

            calander.set(java.util.Calendar.YEAR, startYear);
            calander.set(java.util.Calendar.MONTH, startMonth - 1); // month 는 0 이 1월이다
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
            calander.set(java.util.Calendar.MONTH, endMonth - 1); // month 는 0 이 1월이다
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
     * 안드로이드 디바이스에 Google Play Services가 설치 안되어 있거나 오래된 버전인 경우 보여주는 대화상자
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
