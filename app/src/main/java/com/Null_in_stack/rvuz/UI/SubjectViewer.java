package com.Null_in_stack.rvuz.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.Null_in_stack.rvuz.ScheduleM.Subject;
import com.Null_in_stack.rvuz.ScheduleM.Task;
import com.Null_in_stack.rvuz.R;

import java.util.Objects;

public class SubjectViewer extends AppCompatActivity implements View.OnClickListener {

    private EditText HomeText;
    private Subject subject;
    private int day;
    private ImageButton delete;
    private ImageButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_viuver);

        HomeText = findViewById(R.id.home_task);
        HomeText.setOnClickListener(this);
        HomeText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    Objects.requireNonNull(imm).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
                return false;
            }
        });

        delete = findViewById(R.id.delete_task);
        delete.setOnClickListener(this);
        add = findViewById(R.id.add_task);
        add.setOnClickListener(this);

        subject = (Subject) getIntent().getExtras().get("subject");
        day = getIntent().getExtras().getInt("day");

        if (subject != null) {
            Task t = MainActivity.DB.get(subject.getTeacher() + day);
            if (t != null) {
                HomeText.setText(String.valueOf(t.getTask()));
                HomeText.setEnabled(false);
            }
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_task:
                HomeText.setSelected(true);
                break;

            case R.id.delete_task:
                if (HomeText.getText() != null && String.valueOf(HomeText.getText()).length() > 0) {
                    MainActivity.DB.removeSingleContact(subject.getTeacher() + day);
                    MainActivity.DB.get();
                }
                HomeText.setEnabled(true);
                HomeText.setText("");
                break;

            case R.id.add_task:
                if (HomeText.getText() != null && String.valueOf(HomeText.getText()).length() > 0) {
                    MainActivity.DB.Save(subject.getTeacher(), day, String.valueOf(HomeText.getText()));
                    MainActivity.DB.get();
                }
                finish();
                break;
        }
    }
}
