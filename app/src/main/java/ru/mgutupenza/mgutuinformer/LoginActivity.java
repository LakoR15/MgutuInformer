package ru.mgutupenza.mgutuinformer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.mgutupenza.mgutuinformer.tasks.LoginTask;

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button signIn;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initToolbar();

        email = (EditText) findViewById(R.id.signin);
        password = (EditText) findViewById(R.id.signin_password);

        signIn = (Button) findViewById(R.id.signin_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().matches(Patterns.EMAIL_ADDRESS.toString()) && password.getText().length() != 0){
                    LoginTask task = new LoginTask(LoginActivity.this, getApplicationContext(), email.getText().toString(), password.getText().toString());
                    task.execute();
                }else {
                    Toast.makeText(getApplicationContext(), "Проверьте введеные данные", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            getSupportActionBar().setTitle("Вход");
        }

    }
}
