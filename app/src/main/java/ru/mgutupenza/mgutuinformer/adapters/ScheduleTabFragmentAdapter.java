package ru.mgutupenza.mgutuinformer.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.mgutupenza.mgutuinformer.R;
import ru.mgutupenza.mgutuinformer.model.server.Schedule;
import ru.mgutupenza.mgutuinformer.model.server.Weekday;

public class ScheduleTabFragmentAdapter extends PagerAdapter {

    private Context context;
    private List<Schedule> schedules;

    public ScheduleTabFragmentAdapter(Context context, List<Schedule> schedules) {
        this.context = context;
        this.schedules = schedules;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Weekday weekday = Weekday.values()[position];
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.fragment_day_tab, container, false);

        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.rv_shedule);
        TextView textView = (TextView) layout.findViewById(R.id.message_schedule_tab);
        List<Schedule> schedules = getScheduleSubjectsDay(weekday);
        if(schedules.isEmpty()){
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            textView.setVisibility(View.GONE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            SheduleRVAdapter adapter = new SheduleRVAdapter(schedules, context);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }

        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return Weekday.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Weekday.values()[position].getName();
    }

    private List<Schedule> getScheduleSubjectsDay(Weekday weekday){

        List<Schedule> schedules = new ArrayList<>();
        for (Schedule ss: this.schedules) {
            if(ss.getWeekday() == weekday){
                schedules.add(ss);
            }
        }
        return schedules;
    }
}
