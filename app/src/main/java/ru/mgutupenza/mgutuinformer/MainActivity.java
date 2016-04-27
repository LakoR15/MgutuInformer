package ru.mgutupenza.mgutuinformer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

import ru.mgutupenza.mgutuinformer.fragments.NewsFragment;
import ru.mgutupenza.mgutuinformer.fragments.SheduleFragment;
import ru.mgutupenza.mgutuinformer.utils.FileIO;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "Main Activity";

    NewsFragment newsFragment;
    SheduleFragment sheduleFragment;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        newsFragment = new NewsFragment();
        sheduleFragment = new SheduleFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (savedInstanceState == null) {
            fragmentTransaction.replace(R.id.container, newsFragment, NewsFragment.TAG);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        fragmentTransaction = fragmentManager.beginTransaction();

        int id = item.getItemId();

        if (id == R.id.nav_news) {

            fragmentTransaction.replace(R.id.container, newsFragment, NewsFragment.TAG);

        } else if (id == R.id.nav_shedulle) {

            fragmentTransaction.replace(R.id.container, sheduleFragment, SheduleFragment.TAG);

        } else if(id == R.id.menu_sign_in_up){
            startActivity(new Intent(this, StartActivity.class));
//        } else if (id == R.id.nav_im) {
//
//        } else if (id == R.id.nav_group) {
//
//        } else if (id == R.id.nav_settings) {
//
      }

        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


