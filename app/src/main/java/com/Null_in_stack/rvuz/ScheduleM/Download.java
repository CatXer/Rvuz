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
            URL url = new URL("https://kosygin-rgu.ru/filemanag/Uploads/raspisan-2018-2019-1sem/mehatroniki/18092018/%D0%9C%D0%B5%D1%85%D0%B0%D1%82%D1%80%D0%BE%D0%BD%D0%B8%D0%BA%D0%B8%201%20%D0%BA%D1%83%D1%80%D1%81%20%D0%BE%D1%81%D0%B5%D0%BD%D1%8C%202018.xls");
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
