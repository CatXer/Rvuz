package com.Null_in_stack.rvuz.UI;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.Null_in_stack.rvuz.ScheduleM.DataBase;
import com.Null_in_stack.rvuz.ScheduleM.Rasp;
import com.example.serg.rvuz.R;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @SuppressLint("StaticFieldLeak")
    public static DataBase DB;
    public static Rasp rasp;
    private TextView raspText;
    private TextView tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DataBase(this);

        rasp = new Rasp(this);

        raspText = findViewById(R.id.rasp);
        tasks = findViewById(R.id.tasks);
        raspText.setOnClickListener(this);
        tasks.setOnClickListener(this);

        RaspFragment raspFragment = new RaspFragment();
        setFragment(raspFragment, R.id.fragment_frame, R.anim.slide_in_left, R.anim.slide_out_right, false);
        raspText.setTextColor(Color.parseColor("#00f708"));
        tasks.setTextColor(Color.parseColor("#ffffff"));
    }

    public void setFragment(Fragment fragment, int id, int animation_in, int animation_out, boolean addToBack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(animation_in, animation_out, R.anim.slide_in_left, R.anim.slide_out_right);
        if (addToBack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rasp:
                //mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                raspText.setTextColor(Color.parseColor("#00f708"));
                tasks.setTextColor(Color.parseColor("#ffffff"));
                RaspFragment raspFragment = new RaspFragment();
                setFragment(raspFragment, R.id.fragment_frame, R.anim.slide_in_left, R.anim.slide_out_right, false);
                break;
            case R.id.tasks:
                tasks.setTextColor(Color.parseColor("#00f708"));
                raspText.setTextColor(Color.parseColor("#ffffff"));
                //mMainNav.setItemBackgroundResource(R.color.colorAccent);
                //setFragment(scheduleFragment, R.id.fragment_frame, R.anim.slide_in_right, R.anim.slide_out_left, false);
                break;
        }
    }
}


