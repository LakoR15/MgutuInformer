package ru.mgutupenza.mgutuinformer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;

import ru.mgutupenza.mgutuinformer.R;

public class SheduleFragment extends Fragment {

    public static final String TAG = "SheduleFragmentTag";

    private ViewPager viewPager;
    private Spinner spinner;
    private LinearLayout changeGroupLayout, tabLayout;
    private TabLayout tabSchedule;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_shedule, container, false);
        changeGroupLayout = (LinearLayout) v.findViewById(R.id.change_group_layout);
        tabLayout = (LinearLayout) v.findViewById(R.id.tab_layout);
        tabSchedule = (TabLayout) getActivity().findViewById(R.id.tab_shedule);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.shedule);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//        spinner = (Spinner) getActivity().findViewById(R.id.spinner_nav);
//        spinner.setVisibility(View.VISIBLE);

        return v;
    }

    @Override
    public void onDestroyView() {
        if (spinner != null) {
            spinner.setVisibility(View.GONE);
        }
        if (tabSchedule != null){
            tabSchedule.setVisibility(View.GONE);
        }
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
            if (tabLayout.getVisibility() == View.GONE) {
                changeGroupLayout.setVisibility(View.GONE);
                tabLayout.setVisibility(View.VISIBLE);
                tabSchedule.setVisibility(View.VISIBLE);
            } else {
                changeGroupLayout.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.GONE);
                tabSchedule.setVisibility(View.GONE);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
