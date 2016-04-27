package ru.mgutupenza.mgutuinformer.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.RemoteInput;
import android.support.v4.widget.SwipeRefreshLayout;
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
import ru.mgutupenza.mgutuinformer.utils.FileIO;

public class NewsTask extends AsyncTask<Void, Void, String> {

    Context context;
    SwipeRefreshLayout swipeRefreshLayout;

    public NewsTask(Context context) {
        this.context = context;
    }

    public NewsTask(Context context, SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
//        BufferedReader reader = null;
//
//        try {
//
//            URL url = new URL("https://api.vk.com/method/wall.get?domain=mgutupkit&offset=0&count=100&filter=all&extended=1&version=5.42");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.connect();
//            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuilder buf = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                buf.append(line);
//            }
//            return buf.toString();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//            Log.e("NewsTask", "Error availability server " + e.getMessage());
//            return "";
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.e("NewsTask", "Error reading data " + e.getMessage());
//            return "";
//        } finally {
//            if (reader != null)
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Log.e("NewsTask", "Error close reader " + e.getMessage());
//                }
//        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.vk.com/method/wall.get?domain=mgutupkit&offset=0&count=100&filter=all&extended=1&version=5.42")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("NewsTask", "Error availability server " + e.getMessage());
            return "";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (!s.equals("")) {
            FileIO.saveString("News", s, context);
        }
        if (swipeRefreshLayout != null)
        swipeRefreshLayout.setRefreshing(false);
    }


}
