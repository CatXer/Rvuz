package com.Null_in_stack.rvuz.ScheduleM;

import java.util.ArrayList;

public class Day {

    private String DayType;
    private ArrayList subjects;

    Day(ArrayList subjects, String Name) {
        DayType = Name;
        this.subjects = subjects;

    }

    public String getDayType() {
        return DayType;
    }

    public ArrayList getSubjects() {
        return subjects;
    }
}
