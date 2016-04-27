package ru.mgutupenza.mgutuinformer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.mgutupenza.mgutuinformer.tasks.NewsTask;
import ru.mgutupenza.mgutuinformer.tasks.ScheduleTask;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        NewsTask task = new NewsTask(getApplicationContext());
        task.execute();
        ScheduleTask task1 = new ScheduleTask(this, getApplicationContext());
        task1.execute();
    }

}
