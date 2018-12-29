package com.Null_in_stack.rvuz.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Null_in_stack.rvuz.ScheduleM.DateT;
import com.example.serg.rvuz.R;



public class RaspFragment extends Fragment {

    private TextView typeOfWeek;
    private TextView WeekCounter;

    private ViewPager viewPager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rasp, container, false);

        typeOfWeek = v.findViewById(R.id.typeOfWeek);
        WeekCounter = v.findViewById(R.id.WeekCounter);
        viewPager = v.findViewById(R.id.view_pager);

        SpinnerCalendar myAdapter = new SpinnerCalendar(getContext(), this, DateT.getCurrentLessDay());


        ImageView nextWeek = v.findViewById(R.id.next_week);
        nextWeek.setOnClickListener(myAdapter);
        ImageView backWeek = v.findViewById(R.id.back_week);
        backWeek.setOnClickListener(myAdapter);

        viewPager.setAdapter(myAdapter);
        viewPager.setOnPageChangeListener(myAdapter);


        //viewPager.setCurrentItem(DateT.getCurrentLessDay());
        viewPager.setCurrentItem(DateT.getCurrentLessDay());
       /* Calendar calendar =DateT.getRealDate(121);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println(calendar.get(Calendar.DATE));*/

        return v;
    }

    public TextView getTypeOfWeek() {
        return typeOfWeek;
    }

    public TextView getWeekCounter() {
        return WeekCounter;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }
}
