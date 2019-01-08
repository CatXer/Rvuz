package com.Null_in_stack.rvuz.ScheduleM;

import android.content.Context;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutionException;


public class Rasp {
    //private DateT currentTime;
    private Weak Even_week;
    private Weak Odd_week;
    private HSSFSheet myExcelSheet;
    private Context context;

    ////////////////////////////////////////

    private final static int END_POSIT = 52;
    private final static int START_POSIT = 17;
    private final static int DAY_TYPE = 0;
    private final static int LESSONS_TIME = 2;
    private static int classRoom = 3;
    private static int lessonsType = 4;
    private static int teacher = 5;
    private static int lessonsName = 6;

    public Rasp(Context context) {
        this.context = context;
        Init();
    }

    private void Init() {


        try {
            File f;
            if (!context.getFileStreamPath("Data.xls").exists()) {
                Download d = new Download();
                d.execute(context);
                f = d.get();
                System.out.println("Download!");
            }else {f = context.getFileStreamPath("Data.xls");}
            OpenPage(f, "мв-218");

        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        Even_week = new Weak(false);
        Even_week.fillDays(FillWeak(false));

        Odd_week = new Weak(true);
        Odd_week.fillDays(FillWeak(true));
    }

    private ArrayList<Day> FillWeak(boolean odd) {
        ArrayList<Day> days = new ArrayList<>();
        int LESSONS_COUNT = 0;
        int separator = 0;
        if (!odd)
            separator = 1;

        String dayType = null;
        ArrayList<Subject> subjects = new ArrayList<>();
        for (int i = START_POSIT; i < END_POSIT; i++) {
            if (getCV(i, DAY_TYPE) != null)
                dayType = (String) getCV(i, DAY_TYPE);
            if (getCV(i, 1) != null)
                LESSONS_COUNT++;
            if (getCV(i, classRoom + separator * 7) != null) {
                String time = (String) getCV(i, LESSONS_TIME);
                Subject s = new Subject(time.substring(0, Objects.requireNonNull(time).indexOf('-')), time.substring(time.indexOf('-') + 1), getCV(i, classRoom + separator * 7), getCV(i, lessonsType + separator * 5), getCV(i, teacher + separator * 3), getCV(i, lessonsName + separator));
                subjects.add(s);

            }
            if (LESSONS_COUNT == 5) {
                LESSONS_COUNT = 0;
                days.add(new Day(subjects, dayType));
                subjects = new ArrayList<>();
            }
        }
        return days;
    }

    private void OpenPage(File file, String sheetName) throws IOException {
        if (file.exists() && file.isFile()) {
            HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
            myExcelSheet = myExcelBook.getSheet(sheetName);
        }
    }

    private Object getCV(int rowID, int cellID) {
        HSSFRow row = myExcelSheet.getRow(rowID);

        Cell cell = row.getCell(cellID);
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                return (int) cell.getNumericCellValue();
            case HSSFCell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case HSSFCell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
        }
        return null;
    }

    public Weak getEven_week() {
        return Even_week;
    }

    public Weak getOdd_week() {
        return Odd_week;
    }
}
