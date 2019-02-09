package com.Null_in_stack.rvuz.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Null_in_stack.rvuz.ScheduleM.DateT;
import com.Null_in_stack.rvuz.R;

import java.util.Calendar;
import java.util.Objects;


public class SpinnerCalendar extends PagerAdapter implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private Context context;
    private RaspFragment parent;
    private int currentPage = 0;


    SpinnerCalendar(Context context, RaspFragment raspFragment, int day) {
        this.context = context;
        parent = raspFragment;
        onPageSelected(day);
    }


    @Override
    public int getCount() {
        return DateT.TOTAL_DAYS;
    }

    private void Init(View viewContainer, int position) {
        Calendar calendar = DateT.getRealDate(position);

        boolean isEvenWeek = DateT.isEvenWeak(position);

        RecyclerView recyclerSubject = viewContainer.findViewById(R.id.subjects_views);
        recyclerSubject.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        LessonAdapter lessonAdapter = new LessonAdapter();
        recyclerSubject.setAdapter(lessonAdapter);
        ////////////////////////////////////////
        TextView date = viewContainer.findViewById(R.id.day_name);
        TextView restText = viewContainer.findViewById(R.id.rest_text);
        //////////////////////////////////////////
        String WeekDay = DateT.days[calendar.get(Calendar.DAY_OF_WEEK)];
        String Month = DateT.months[calendar.get(Calendar.MONTH)];
        date.setText(String.valueOf(WeekDay + " " + calendar.get(Calendar.DATE) + " " + Month));
        /////////////////////////////////////////
        if (!DateT.days[calendar.get(Calendar.DAY_OF_WEEK)].equals("ВС")&&!DateT.days[calendar.get(Calendar.DAY_OF_WEEK)].equals("СБ")) {
            //System.out.println("ВС is - {" + !DateT.days[calendar.get(Calendar.DAY_OF_WEEK)].equals("ВС") + "} in - [" + position + "]");
            lessonAdapter.Fill(isEvenWeek ? MainActivity.rasp.getEven_week().getDay(WeekDay).getSubjects() : MainActivity.rasp.getOdd_week().getDay(WeekDay).getSubjects(),
                    context, position);
        }

        if (lessonAdapter.getItemCount() > 0)
            restText.setVisibility(View.GONE);
        else restText.setVisibility(View.VISIBLE);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        boolean isEvenWeek = DateT.isEvenWeak(position);

        currentPage = position;


        ////////////////////////////////////////


        int[] startEnd = DateT.getWeekRange(position);


        parent.getTypeOfWeek().setText(isEvenWeek ? "Чётная неделя" : "Нечётная неделя");
        Calendar calendar;
        calendar = DateT.getRealDate(startEnd[0]);
        String timeFirst = calendar.get(Calendar.DATE) + " " + DateT.months[calendar.get(Calendar.MONTH)];
        calendar = DateT.getRealDate(startEnd[1]);
        String timeEnd = calendar.get(Calendar.DATE) + " " + DateT.months[calendar.get(Calendar.MONTH)];

        parent.getWeekCounter().setText(String.valueOf(timeFirst + " - " + timeEnd));


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = (Objects.requireNonNull(inflater)).inflate(R.layout.day_card, container, false);


        Init(view, position);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_week:
                int up = currentPage + 7;
                if (up < DateT.TOTAL_DAYS) {
                    onPageSelected(up);
                    parent.getViewPager().setCurrentItem(up);
                }
                break;
            case R.id.back_week:
                int down = currentPage - 7;
                if (down >= 0) {
                    onPageSelected(down);
                    parent.getViewPager().setCurrentItem(down);
                }
                break;
        }
    }

}
