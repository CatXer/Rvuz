package com.Null_in_stack.rvuz.ScheduleM;

import java.util.Calendar;
import java.util.Date;

public class DateT {
    //    246 - 1 день
    //    182 - последний
    //    119 + 182 = 301 день
    private static Calendar calendar = Calendar.getInstance();
    private static final Date CURRENT_DATE = new Date();
    private final static int START_LESSON_DAY = 246;
    private final static int START_LESSON_NEW_YEAR = 119;
    private final static int END_LESSON_DAY = 182;
    public final static int TOTAL_DAYS = 301;

    public static String months[] = {"Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сенября", "Октября", "Ноября", "Декабря"};
    public static String days[] = {"", "ВС", "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ"};

    public static int getCurrentLessDay() {
        calendar.setTime(CURRENT_DATE);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        if (dayOfYear - DateT.START_LESSON_DAY >= 0)
            return dayOfYear - DateT.START_LESSON_DAY;
        else if (dayOfYear <= END_LESSON_DAY) return dayOfYear + DateT.START_LESSON_NEW_YEAR;
        return 0;
    }

    public static Calendar getRealDate(int lessonDay) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        if (lessonDay <= DateT.START_LESSON_NEW_YEAR) {
            c.set(Calendar.YEAR, c.get(Calendar.YEAR)-1);
            c.set(Calendar.DAY_OF_YEAR, lessonDay + DateT.START_LESSON_DAY);
            return c;
        } else {
            c.set(Calendar.YEAR, c.get(Calendar.YEAR));
            c.set(Calendar.DAY_OF_YEAR, lessonDay - DateT.START_LESSON_NEW_YEAR);
            return c;
        }
    }

    public static boolean isEvenWeak(int lessonDay) {
        return (lessonDay / 7 - 1) % 2 == 0;
    }

    public static int[] getWeekRange(int lessonDay) {

        int[] startEnd;
        int startDay = lessonDay;
        int endDay = lessonDay + 1;

        while (true) {
            if (startDay % 7 == 0 && endDay % 7 == 0) {
                startEnd = new int[]{startDay, endDay - 1};
                break;
            }
            if (startDay % 7 != 0)
                startDay--;
            if (endDay % 7 != 0)
                endDay++;
        }
        return startEnd;
    }
}
