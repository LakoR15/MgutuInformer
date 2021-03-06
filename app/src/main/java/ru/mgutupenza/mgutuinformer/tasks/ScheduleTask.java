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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.mgutupenza.mgutuinformer.MainActivity;
import ru.mgutupenza.mgutuinformer.R;
import ru.mgutupenza.mgutuinformer.SplashScreenActivity;
import ru.mgutupenza.mgutuinformer.utils.FileIO;

public class ScheduleTask extends AsyncTask<Void, Void, String> {

     private Context context;

    public ScheduleTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(context.getString(R.string.URL) + "/api/schedule.get")
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
            FileIO.saveString("Schedule", s, context);
        }

    }
}
