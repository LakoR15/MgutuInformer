package ru.mgutupenza.mgutuinformer.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import ru.mgutupenza.mgutuinformer.MainActivity;
import ru.mgutupenza.mgutuinformer.SplashScreenActivity;
import ru.mgutupenza.mgutuinformer.utils.FileIO;

public class ScheduleTask extends AsyncTask<Void, Void, String> {

     private AppCompatActivity activity;
     private Context context;

    public ScheduleTask(Context context) {
        this.context = context;
    }

    public ScheduleTask(AppCompatActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        BufferedReader reader = null;

        try {

            URL url = new URL("https://mgutuinformer.herokuapp.com/schedule");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder buf = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line);
            }
            return buf.toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Log.e("ScheduleTask", "Error availability server " + e.getMessage());
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ScheduleTask", "Error reading data " + e.getMessage());
            return "";
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("ScheduleTask", "Error close reader " + e.getMessage());
                }
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (!s.equals("")) {
            FileIO.saveString("Schedule", s, context);
        }
        if (activity != null){
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        }

    }
}
