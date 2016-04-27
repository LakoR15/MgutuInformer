package ru.mgutupenza.mgutuinformer.fragments.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.mgutupenza.mgutuinformer.R;
import ru.mgutupenza.mgutuinformer.adapters.SheduleRVAdapter;
import ru.mgutupenza.mgutuinformer.model.server.Schedule;

public class DayTabFragment extends Fragment {

    private String dayOfTheWeek;
    private List<Schedule> sheduleSubjects;

    private RecyclerView recyclerView;

    private SheduleRVAdapter adapter;

    public static DayTabFragment getInstance(String day, List<Schedule> sheduleSubjects) {
        DayTabFragment fragment = new DayTabFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setDayOfTheWeek(day);
        fragment.setSheduleSubjects(sheduleSubjects);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_day_tab, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_shedule);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
//        adapter = new SheduleRVAdapter(sheduleSubjects, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

    public List<Schedule> getSheduleSubjects() {
        return sheduleSubjects;
    }

    public void setSheduleSubjects(List<Schedule> sheduleSubjects) {
        this.sheduleSubjects = sheduleSubjects;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
}
