package com.Null_in_stack.rvuz.ScheduleM;

import java.util.ArrayList;

public class Weak {

    private ArrayList<Day> days;
    private boolean odd;

    Weak(boolean odd) {
        this.odd = odd;
    }

    public void fillDays(ArrayList<Day> days) {
        this.days = days;
    }

    public boolean isOdd() {
        return odd;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public Day getDay(String dayName) {
        for (Day day : days)
            if (dayName.toLowerCase().contains(day.getDayType().toLowerCase()))
                return day;
        return null;
    }
}

