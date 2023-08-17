package com.example.outlab9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ViewPager2 myPage;
    TabLayout myTab;
    ArrayList<String> y1 = new ArrayList<String>();
    ArrayList<String> y2 = new ArrayList<String>();
    ArrayList<String> y3 = new ArrayList<String>();
    ArrayList<String> y4 = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String data[] = {"Study Plan", "Assignment", "Exam", "Lecture"};
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        myTab = (TabLayout)findViewById(R.id.mytab);
        myPage = (ViewPager2) findViewById(R.id.mypager);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        /*FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction t = manager.beginTransaction();
        Study_Plan sp = new Study_Plan();
        //t.add(R.id.drawer,sp);
        t.add(sp, "MainActivity");
        t.commit();*/

        myPage.setAdapter(new MyOwnPagerAdapter(getSupportFragmentManager(), getLifecycle()));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(myTab, myPage, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(data[position]);
            }
        });
        tabLayoutMediator.attach();
        myTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myPage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R.id.homemenu){
            /*Intent i1 = new Intent(this, MainActivity.class);
            startActivity(i1);*/
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.calendermenu){
            //y.add("Hello");
            Intent i2 = new Intent(this, CalenderActivity.class);
            Bundle b = new Bundle();
            b.putStringArrayList("date1", y1);
            b.putStringArrayList("date2", y2);
            b.putStringArrayList("date3", y3);
            b.putStringArrayList("date4", y4);

            i2.putExtras(b);
            startActivity(i2);
            Toast.makeText(this, "Calender", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void f1(ArrayList<String> ls) {
        y1 = ls;
    }
    public void f2(ArrayList<String> ls) {
        y2 = ls;
    }
    public void f3(ArrayList<String> ls) {
        y3 = ls;
    }
    public void f4(ArrayList<String> ls) {
        y4 = ls;
    }

    class MyOwnPagerAdapter extends FragmentStateAdapter {

        String data[] = {"Study Plan", "Assignment", "Exam", "Lecture"};

        public MyOwnPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }


        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0){
                return new Study_Plan();
            }
            if (position == 1){
                return new Assignment();
            }
            if (position == 2){
                return new Exam();
            }
            if (position == 3){
                return  new Lecture();
            }


            return null;
        }

        @Override
        public int getItemCount() {
            return data.length;
        }
    }


    private class OnConfigureTabCallback {
    }
}