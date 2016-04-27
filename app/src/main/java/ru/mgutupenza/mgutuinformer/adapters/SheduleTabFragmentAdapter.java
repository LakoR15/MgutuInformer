package ru.mgutupenza.mgutuinformer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.mgutupenza.mgutuinformer.fragments.tabs.DayTabFragment;
import ru.mgutupenza.mgutuinformer.model.server.Schedule;

public class SheduleTabFragmentAdapter extends FragmentPagerAdapter {

    private List<Schedule> shedules = new ArrayList<>();
    private Map<Integer, DayTabFragment> tabs;
    private Context context;

    public SheduleTabFragmentAdapter(Context context, FragmentManager fm, List<Schedule> shedules) {
        super(fm);
        this.context = context;
        this.shedules.addAll(shedules);
//        initTabsMap(context);


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

//    private void initTabsMap(Context context) {
//        tabs = new HashMap<>();
//        tabs.put(0, DayTabFragment.getInstance(context.getString(R.string.monday), getSheduleSubjectsDay(0)));
//        tabs.put(1, DayTabFragment.getInstance(context.getString(R.string.tuesday), getSheduleSubjectsDay(1)));
//        tabs.put(2, DayTabFragment.getInstance(context.getString(R.string.wednesday), getSheduleSubjectsDay(2)));
//        tabs.put(3, DayTabFragment.getInstance(context.getString(R.string.thursday), getSheduleSubjectsDay(3)));
//        tabs.put(4, DayTabFragment.getInstance(context.getString(R.string.friday), getSheduleSubjectsDay(4)));
//        tabs.put(5, DayTabFragment.getInstance(context.getString(R.string.saturday), getSheduleSubjectsDay(5)));
//
//    }
//
//    private List<Shedule> getSheduleSubjectsDay(int dayNumber){
//
//        List<Shedule> shedules = new ArrayList<>();
//        for (Shedule ss: this.shedules) {
//            if(ss.getDayNumber() == dayNumber){
//                shedules.add(ss);
//            }
//        }
//
//        return shedules;
//    }
}
