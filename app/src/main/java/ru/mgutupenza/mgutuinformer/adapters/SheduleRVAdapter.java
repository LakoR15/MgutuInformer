package ru.mgutupenza.mgutuinformer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.mgutupenza.mgutuinformer.R;
import ru.mgutupenza.mgutuinformer.model.server.Schedule;
import ru.mgutupenza.mgutuinformer.utils.Converter;

public class SheduleRVAdapter extends RecyclerView.Adapter<SheduleRVAdapter.SheduleViewHolder> {

    List<Schedule> schedules;
    Context context;

    public SheduleRVAdapter(List<Schedule> schedules, Context context) {
        this.schedules = schedules;
        this.context = context;
    }

    @Override
    public SheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shedule, parent, false);
        return new SheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SheduleViewHolder holder, int position) {

//        String time = schedules.get(position).getLessonTime().getLessonTimeStart() + "-" +schedules.get(position).getLessonTime().getLessonTimeEnd();
        holder.timeTextView.setText(Converter.toString(schedules.get(position).getLessonTime().getLessonTimeStart(), schedules.get(position).getLessonTime().getLessonTimeEnd()));
        holder.classroomTextView.setText(schedules.get(position).getLectureHall().getLectureHallName());
        holder.subjectTextView.setText(schedules.get(position).getEmploymentType().getEmploymentTypeName());
        holder.nameSubjectTextView.setText(schedules.get(position).getDiscipline().getDisciplineName());
        holder.teacherTextView.setText(schedules.get(position).getTeacher().getTeacherName());
        if (position % 2 == 0){
            holder.divider.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_light));
        }else {
            holder.divider.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_light));
        }


    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public static class SheduleViewHolder extends RecyclerView.ViewHolder {

        private TextView timeTextView;
        private TextView classroomTextView;
        private TextView subjectTextView;
        private TextView nameSubjectTextView;
        private TextView teacherTextView;
        private View divider;

        public SheduleViewHolder(View itemView) {
            super(itemView);
            timeTextView = (TextView) itemView.findViewById(R.id.shedule_time);
            classroomTextView = (TextView) itemView.findViewById(R.id.shedule_classroom);
            subjectTextView = (TextView) itemView.findViewById(R.id.shedule_subject);
            nameSubjectTextView = (TextView) itemView.findViewById(R.id.shedule_subject_name);
            teacherTextView = (TextView) itemView.findViewById(R.id.shedule_teacher);
            divider = itemView.findViewById(R.id.view);
        }
    }

}
