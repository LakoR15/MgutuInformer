package ru.mgutupenza.mgutuinformer.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.mgutupenza.mgutuinformer.MainActivity;
import ru.mgutupenza.mgutuinformer.model.server.Groups;
import ru.mgutupenza.mgutuinformer.model.server.Users;
import ru.mgutupenza.mgutuinformer.utils.FileIO;

public class RegistrationTask extends AsyncTask<Void, Void, String>{

    private AppCompatActivity activity;
    private Context context;
    private String name, groupsName, login, password;
    ProgressDialog progressDialog;

    public RegistrationTask(AppCompatActivity activity, Context context, String name, String groupsName, String login, String password) {
        this.activity =  activity;
        this.context = context;
        this.name = name;
        this.groupsName = groupsName;
        this.login = login;
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
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://mgutuinformer.herokuapp.com/users/registration?name="+ name +"&groupsName="+ groupsName +"&login="+ login +"&password="+password+"&student=TRUE")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
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
            //TODO заменить данные пользователя на данные с сервера
            java.lang.reflect.Type type = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> result = new Gson().fromJson(s, type);
            Users users = new Users();
//            users.setId(Long.valueOf(result.get("usersId")));
            users.setName(name);
            users.setGroups(new Groups(groupsName));
            users.setSecretKey(result.get("secretKey"));
            String user = new Gson().toJson(users);
            FileIO.saveString("CurrentUser", user, context);
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            if(activity != null){
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        }else{
            Toast.makeText(context, "Регистрация не удалась, попробуйте позже", Toast.LENGTH_SHORT).show();
        }
    }
}
