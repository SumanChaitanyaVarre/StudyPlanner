package com.example.outlab9;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class CalenderActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ArrayList<String> a1 = new ArrayList<>();
    ArrayList<String> a2 = new ArrayList<>();
    ArrayList<String> a3 = new ArrayList<>();
    ArrayList<String> a4 = new ArrayList<>();
    CompactCalendarView compactCalendar;
    DrawerLayout drawerLayout;
    TextView myDate, noOfLectures, studyplans, noOfExams, noOfAssignments, month;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    TextView t1;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 10);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, - 10);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        compactCalendar = (CompactCalendarView) findViewById(R.id.calendarView3);
        //calendarView = (CalendarView) findViewById(R.id.calendarView3);
        //calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        //calendarView = (MCalendarView) findViewById(R.id.calendarView3);
        myDate = (TextView) findViewById(R.id.myDate);
        noOfLectures = (TextView) findViewById(R.id.noOfLectures);
        studyplans = (TextView) findViewById(R.id.noOfStudyplans);
        noOfExams = (TextView) findViewById(R.id.noOfExams);
        noOfAssignments = (TextView) findViewById(R.id.noOfAssignments);
        month = (TextView) findViewById(R.id.monthview);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        Intent i1 = getIntent();
        Bundle b1 = i1.getExtras();
        a1 = b1.getStringArrayList("date1");
        a2 = b1.getStringArrayList("date2");
        a3 = b1.getStringArrayList("date3");
        a4 = b1.getStringArrayList("date4");
        compactCalendar.setUseThreeLetterAbbreviation(true);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM d, yyyy", Locale.getDefault());

        for (int i = 0; i < a1.size(); i++) {
            String currentIndexDate = a1.get(i);
            Date date = null;
            try {
                date = sdf.parse(currentIndexDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert date != null;
            long milliseconds = date.getTime();

            Event ev;
            ev = new Event(Color.RED, milliseconds,"study plans");
            compactCalendar.addEvent(ev);
        }

        for (int i = 0; i < a2.size(); i++) {
            String currentIndexDate = a2.get(i);
            Date date = null;
            try {
                date = sdf.parse(currentIndexDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert date != null;
            long milliseconds = date.getTime();

            Event ev;
            ev = new Event(Color.GREEN, milliseconds,"assignments");
            compactCalendar.addEvent(ev);
        }

        for (int i = 0; i < a3.size(); i++) {
            String currentIndexDate = a3.get(i);
            Date date = null;
            try {
                date = sdf.parse(currentIndexDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert date != null;
            long milliseconds = date.getTime();

            Event ev;
            ev = new Event(Color.BLUE, milliseconds,"exams");
            compactCalendar.addEvent(ev);
        }

        for (int i = 0; i < a4.size(); i++) {
            String currentIndexDate = a4.get(i);
            Date date = null;
            try {
                date = sdf.parse(currentIndexDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert date != null;
            long milliseconds = date.getTime();

            Event ev;
            ev = new Event(Color.BLACK, milliseconds,"lectures");
            compactCalendar.addEvent(ev);
        }

    /*    calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, dayOfMonth);
                String currentDate = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();
                myDate.setText(currentDate);
                String lectures = Integer.toString(Collections.frequency(a4, currentDate));
                String study_plans = Integer.toString(Collections.frequency(a1, currentDate));
                String exams = Integer.toString(Collections.frequency(a3, currentDate));
                String assignments = Integer.toString(Collections.frequency(a2, currentDate));


                noOfLectures.setText(lectures);
                studyplans.setText(study_plans);
                noOfExams.setText(exams);
                noOfAssignments.setText(assignments);

            }
        }); */

        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        //getSupportActionBar().setTitle(null);


        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                //Toast.makeText(context, dateClicked.toString(), Toast.LENGTH_SHORT).show();
                String currentDate = DateFormat.format("EEEE, MMM d, yyyy", dateClicked).toString();
                //Toast.makeText(context, currentDate, Toast.LENGTH_SHORT).show();
                /*String currentDate = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();
                String currDate = (String) DateFormat.format("EEE, dd MMM YYYY hh:mm:ss z", Long.parseLong(calendar1.toString()));
                myDate.setText(currentDate);*/
                /*Calendar calendar1 = Calendar.getInstance();

                String currentDate = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();
                String currDate = (String) DateFormat.format("EEE, dd MMM YYYY hh:mm:ss z", Long.parseLong(calendar1.toString()));
                */
                myDate.setText(currentDate);
                String lectures = Integer.toString(Collections.frequency(a4, currentDate));
                String study_plans = Integer.toString(Collections.frequency(a1, currentDate));
                String exams = Integer.toString(Collections.frequency(a3, currentDate));
                String assignments = Integer.toString(Collections.frequency(a2, currentDate));


                noOfLectures.setText(lectures);
                studyplans.setText(study_plans);
                noOfExams.setText(exams);
                noOfAssignments.setText(assignments);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                month.setText(new SimpleDateFormat("MMM yyyy").format(compactCalendar.getFirstDayOfCurrentMonth()));
                //Objects.requireNonNull(getSupportActionBar()).setTitle((CharSequence) firstDayOfNewMonth);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R.id.homemenu){
            Intent intent1 = new Intent(this, MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent1);
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.calendermenu){
            /*Intent intent2 = new Intent(this, CalenderActivity.class);
            startActivity(intent2);*/
            Toast.makeText(this, "Calender", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}