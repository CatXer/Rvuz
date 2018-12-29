package com.Null_in_stack.rvuz.ScheduleM;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;

public class DataBase {
    private static SQLiteDatabase myDB;
    private Context context;

    public DataBase(Context context) {
        this.context = context;
    }

    public void Save(String teacher, int day, String text) {
        myDB = context.openOrCreateDatabase("my.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS tasks (id TEXT, teacher TEXT, lesson_date INT, task TEXT)");
        removeSingleContact(teacher + day, myDB);
        ContentValues row1 = new ContentValues();
        row1.put("id", teacher + day);
        row1.put("teacher", teacher);
        row1.put("lesson_date", day);
        row1.put("task", text);
        myDB.insert("tasks", null, row1);
        myDB.close();
    }

    public void get() {
        myDB = context.openOrCreateDatabase("my.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS tasks (id TEXT, teacher TEXT, lesson_date INT, task TEXT)");
        Cursor myCursor = myDB.rawQuery("select id, teacher, lesson_date, task from tasks", null);
        while (myCursor.moveToNext()) {
            String id = myCursor.getString(0);
            String teacher = myCursor.getString(1);
            int lesson_date = myCursor.getInt(2);
            String task = myCursor.getString(3);
            System.out.println(id + " " + teacher + " " + lesson_date + " " + task);
        }
        myCursor.close();
        myDB.close();
    }


    public void removeSingleContact(String id_id) {
        myDB = context.openOrCreateDatabase("my.db", MODE_PRIVATE, null);
        myDB.execSQL("DELETE FROM  tasks  WHERE id = '" + id_id + "'");
        myDB.close();
        System.out.println("removed");
    }

    public void removeSingleContact(String id_id, SQLiteDatabase database) {
        database.execSQL("DELETE FROM  tasks  WHERE id = '" + id_id + "'");
        System.out.println("removed");
    }

    public Task get(String id) {
        myDB = context.openOrCreateDatabase("my.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS tasks (id TEXT, teacher TEXT, lesson_date INT, task TEXT)");

        String Query = "Select teacher, lesson_date, task from tasks where id = '" + id + "'";
        Cursor cursor = myDB.rawQuery(Query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            System.out.println(Arrays.toString(cursor.getColumnNames()));
            return new Task(cursor.getString(2), cursor.getInt(1), cursor.getString(0));
        }
        cursor.close();
        return null;
    }
}
