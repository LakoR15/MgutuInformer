package ru.mgutupenza.mgutuinformer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.mgutupenza.mgutuinformer.R;
import ru.mgutupenza.mgutuinformer.fragments.tabs.DayTabFragment;
import ru.mgutupenza.mgutuinformer.model.server.Schedule;
import ru.mgutupenza.mgutuinformer.model.server.Weekday;

public class SheduleTabFragmentAdapter extends FragmentPagerAdapter {

    private List<Schedule> schedules = new ArrayList<>();
    private Map<Integer, DayTabFragment> tabs;
    private Context context;

    public SheduleTabFragmentAdapter(Context context, FragmentManager fm, List<Schedule> shedules) {
        super(fm);
        this.context = context;
        this.schedules.addAll(shedules);
        initTabsMap(context);


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getDayOfTheWeek();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();
        tabs.put(0, DayTabFragment.getInstance(context.getString(R.string.monday), getScheduleSubjectsDay(Weekday.Monday)));
        tabs.put(1, DayTabFragment.getInstance(context.getString(R.string.tuesday), getScheduleSubjectsDay(Weekday.Tuesday)));
        tabs.put(2, DayTabFragment.getInstance(context.getString(R.string.wednesday), getScheduleSubjectsDay(Weekday.Wednesday)));
        tabs.put(3, DayTabFragment.getInstance(context.getString(R.string.thursday), getScheduleSubjectsDay(Weekday.Thursday)));
        tabs.put(4, DayTabFragment.getInstance(context.getString(R.string.friday), getScheduleSubjectsDay(Weekday.Friday)));
        tabs.put(5, DayTabFragment.getInstance(context.getString(R.string.saturday), getScheduleSubjectsDay(Weekday.Saturday)));

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
