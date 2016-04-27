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

public class SheduleRVAdapter extends RecyclerView.Adapter<SheduleRVAdapter.SheduleViewHolder> {

    List<Schedule> shedules;
    Context context;

    public SheduleRVAdapter(List<Schedule> shedules, Context context) {
        this.shedules = shedules;
        this.context = context;
    }

    @Override
    public SheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shedule, parent, false);
        return new SheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SheduleViewHolder holder, int position) {

//        String str = shedules.get(position).getTimeStart() + " - " + shedules.get(position).getTimeEnd();
//
//        holder.timeTextView.setText(str);
//        holder.classroomTextView.setText(shedules.get(position).getClassroom());
//        holder.subjectTextView.setText(shedules.get(position).getSubject());
//        holder.nameSubjectTextView.setText(shedules.get(position).getSubjectName());
//        holder.teacherTextView.setText(shedules.get(position).getTeacher());
//        if (position % 2 == 0){
//            holder.divider.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_light));
//        }else {
//            holder.divider.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_light));
//        }


    }

    @Override
    public int getItemCount() {
        return shedules.size();
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
