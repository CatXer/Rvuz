package com.Null_in_stack.rvuz.ScheduleM;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Download extends AsyncTask<Context, Void, File> {
    @Override
    protected File doInBackground(Context... voids) {


        try {
            File file = new File(voids[0].getFilesDir(), "Data.xls");
            URL url = new URL("https://kosygin-rgu.ru/1AppRGU/rguschedule/uploads/ClassSchedules/2019/02/08/201902082251599139.xls");
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }

            fis.close();
            bis.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
