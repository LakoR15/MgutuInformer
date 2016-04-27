package ru.mgutupenza.mgutuinformer.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.mgutupenza.mgutuinformer.MainActivity;
import ru.mgutupenza.mgutuinformer.utils.FileIO;

public class LoginTask extends AsyncTask<Void, Void, String> {

    private AppCompatActivity activity;
    private Context context;
    private String email, password;
    ProgressDialog progressDialog;

    public LoginTask(AppCompatActivity activity, Context context, String email, String password) {
        this.activity = activity;
        this.context = context;
        this.email = email;
        this.password = password;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Подключение");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://mgutuinformer.herokuapp.com/users/login?login="+ email +"&password="+ password)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                return response.body().string();
            }else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("RegistrationTask", "Error availability server " + e.getMessage());
            return "";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        if (!s.equals("")){
            FileIO.saveString("CurrentUser", s, context);
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            if(activity != null){
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        }else{
            Toast.makeText(context, "Вход не удался", Toast.LENGTH_SHORT).show();
        }
    }
}
