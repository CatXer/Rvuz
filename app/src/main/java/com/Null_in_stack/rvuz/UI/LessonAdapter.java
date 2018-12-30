package com.Null_in_stack.rvuz.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Null_in_stack.rvuz.ScheduleM.Subject;
import com.Null_in_stack.rvuz.R;

import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonHolder> {

    private ArrayList<Subject> items = new ArrayList<>();
    private Context context;
    private int lessonDay;

    public void Fill(ArrayList<Subject> subjects, Context context, int position) {
        items.addAll(subjects);
        this.context = context;
        lessonDay = position;
    }


    @Override
    public LessonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_card, parent, false);
        return new LessonHolder(view);
    }

    @Override
    public void onBindViewHolder(LessonHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class LessonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //private TextView Index;
        private TextView StartTime;
        private TextView EndTime;
        private TextView Class;
        private TextView Type;
        private TextView Subj;
        private TextView Teacher;
        private LinearLayout les_card;
        private Subject subject;

        LessonHolder(View itemView) {
            super(itemView);
            les_card = itemView.findViewById(R.id.lesson_card);
            StartTime = itemView.findViewById(R.id.graph_start_time);
            EndTime = itemView.findViewById(R.id.graph_end_time);
            Class = itemView.findViewById(R.id.graph_class);
            Type = itemView.findViewById(R.id.graph_type);
            Subj = itemView.findViewById(R.id.graph_subj);
            Teacher = itemView.findViewById(R.id.graph_teacher);

        }

        void bind(Subject subject) {
            String type = null;
            switch (subject.getType()) {
                case "лек":
                    type = "лекция";
                    break;
                case "пр":
                    type = "практика";
                    break;
                case "лаб":
                    type = "лаба";
                    break;
            }

            this.subject = subject;

            StartTime.setText(subject.getStartTime());
            EndTime.setText(subject.getEndTime());
            Class.setText(subject.getClassRoom());
            Type.setText(type);
            Subj.setText(subject.getName());
            les_card.setOnClickListener(this);
            Teacher.setText(subject.getTeacher());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.lesson_card:
                    // Toast.makeText(context, subject.getTeacher(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, SubjectViewer.class);
                    intent.putExtra("subject", subject);
                    intent.putExtra("day", lessonDay);
                    context.startActivity(intent);
                    break;
            }
        }
    }
}
