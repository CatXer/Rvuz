package com.Null_in_stack.rvuz.ScheduleM;

import android.annotation.SuppressLint;

import java.io.Serializable;

public class Subject implements Serializable {

    private String StartTime;
    private String EndTime;
    private String ClassRoom;
    private String Type;
    private String Teacher;
    private String Name;

    @SuppressLint("UseSparseArrays")
    Subject(Object startTime, Object endTime,Object classRoom, Object type, Object teacher, Object name) {
        StartTime = (String) startTime;
        ClassRoom = String.valueOf(classRoom);
        Type = (String) type;
        Teacher = (String) teacher;
        Name = (String) name;
        EndTime = (String) endTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getClassRoom() {
        return ClassRoom;
    }

    public String getType() {
        return Type;
    }

    public String getTeacher() {
        return Teacher;
    }

    public String getName() {
        return Name;
    }
}
