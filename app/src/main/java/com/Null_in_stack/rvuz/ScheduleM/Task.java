package com.Null_in_stack.rvuz.ScheduleM;

import java.io.Serializable;


public class Task implements Serializable {

    private String id;
    private String task;
    private int LessonDate;
    private String Teacher;


    Task(String task, int lessonDate, String teacher) {
        this.task = task;
        LessonDate = lessonDate;
        Teacher = teacher;
        id = Teacher + lessonDate;
    }

    public String getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public int getLessonDate() {
        return LessonDate;
    }

    public String getTeacher() {
        return Teacher;
    }
}
