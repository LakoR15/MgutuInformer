package ru.mgutupenza.mgutuinformer.fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.mgutupenza.mgutuinformer.R;
import ru.mgutupenza.mgutuinformer.adapters.ScheduleTabFragmentAdapter;
import ru.mgutupenza.mgutuinformer.model.server.Schedule;
import ru.mgutupenza.mgutuinformer.utils.FileIO;

public class SheduleFragment extends Fragment {

    public static final String TAG = "SheduleFragmentTag";

    private ProgressBar progressBar;
    private List<Schedule> schedules;
    private LinearLayout changeGroupLayout;
    private Spinner spinnerGroup;
    private Button choiseGroupButton;
    private LinearLayout tabLayout;
    private TabLayout tabSchedule;
    private Spinner spinnerWeekNumber;
    private ViewPager viewPager;
    ScheduleTabFragmentAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_shedule, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.shedule);
        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar_schedule);
        changeGroupLayout = (LinearLayout) v.findViewById(R.id.change_group_layout);
        spinnerGroup = (Spinner) v.findViewById(R.id.group_spinner);
        tabLayout = (LinearLayout) v.findViewById(R.id.tab_layout);
        tabSchedule = (TabLayout) getActivity().findViewById(R.id.tab_shedule);
        viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        spinnerWeekNumber = (Spinner) getActivity().findViewById(R.id.spinner_nav);
        final ScheduleTask task = new ScheduleTask();
        task.execute();
        choiseGroupButton = (Button) v.findViewById(R.id.apply_change_group_button);
        choiseGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeGroupLayout.setVisibility(View.GONE);
                tabLayout.setVisibility(View.VISIBLE);
                tabSchedule.setVisibility(View.VISIBLE);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
                spinnerWeekNumber.setVisibility(View.VISIBLE);
                adapter = new ScheduleTabFragmentAdapter(getContext(), getScheduleByGroup(schedules,spinnerGroup.getSelectedItem().toString()));
                adapter.notifyDataSetChanged();
                viewPager.setAdapter(adapter);
                tabSchedule.setupWithViewPager(viewPager);

            }
        });

        spinnerWeekNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerWeekNumber.getSelectedItem().toString();
                adapter = new ScheduleTabFragmentAdapter(getContext(), getScheduleByWeek(getScheduleByGroup(schedules, spinnerGroup.getSelectedItem().toString()), spinnerWeekNumber.getSelectedItem().toString()));
                viewPager.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        if (spinnerWeekNumber != null) {
            spinnerWeekNumber.setVisibility(View.GONE);
        }
        if (tabSchedule != null){
            tabSchedule.setVisibility(View.GONE);
        }
        adapter = null;
        viewPager = null;
        tabSchedule = null;
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.shedule_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.change_group) {
//
        }
        return super.onOptionsItemSelected(item);
    }

    private List<String> getGroupsName(List<Schedule> schedules){
        List<String> groupNames = new ArrayList<>();
        for(Schedule s: schedules){
            groupNames.add(s.getGroups().getGroupsName());
        }
        HashSet<String> hs = new HashSet<>();
        hs.addAll(groupNames);
        groupNames.clear();
        groupNames.addAll(hs);
        return groupNames;
    }

    private List<Schedule> getScheduleByGroup(List<Schedule> schedules, String group){
        List<Schedule> schedulesByGroup = new ArrayList<>();
        for (Schedule s: schedules){
            if (s.getGroups().getGroupsName().equals(group)){
                schedulesByGroup.add(s);
            }
        }
        return schedulesByGroup;
    }

    private List<Schedule> getScheduleByWeek(List<Schedule> schedules, String week){
        List<Schedule> schedulesByWeek = new ArrayList<>();
        for (Schedule s: schedules){
            if (s.getNumberWeekday().getName().equals(week)){
                schedulesByWeek.add(s);
            }
        }
        return schedulesByWeek;
    }

    public class ScheduleTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(getString(R.string.URL) + "/site/schedule.get")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("ScheduleTask", "Error availability server " + e.getMessage());
                return "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!s.equals("")) {
                FileIO.saveString("Schedule", s, getContext());
                Type itemsListType = new TypeToken<List<Schedule>>() {}.getType();
                schedules = new Gson().fromJson(s, itemsListType);
            }else {
                String str = FileIO.openString("Schedule", getContext());
                Type itemsListType = new TypeToken<List<Schedule>>() {}.getType();
                schedules = new Gson().fromJson(s, itemsListType);
                Toast.makeText(getActivity().getApplicationContext(), "Проверьте соединение с интернетом", Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
            changeGroupLayout.setVisibility(View.VISIBLE);
            ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, getGroupsName(schedules));
            spinnerGroup.setAdapter(adapter);
        }
    }
}
