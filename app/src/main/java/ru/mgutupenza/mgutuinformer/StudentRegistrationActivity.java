package ru.mgutupenza.mgutuinformer;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ru.mgutupenza.mgutuinformer.tasks.RegistrationTask;

public class StudentRegistrationActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button regButton;
    EditText lastName, firstName, email, password, copyPassword;
    TextInputLayout checkPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        initToolbar();
        final Spinner spinner = (Spinner) findViewById(R.id.group);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.group_list, R.layout.spinner_item_group);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

        lastName = (EditText) findViewById(R.id.last_name);
        firstName = (EditText) findViewById(R.id.first_name);
        email = (EditText) findViewById(R.id.user_email);
        password = (EditText) findViewById(R.id.user_password);
        copyPassword = (EditText) findViewById(R.id.copy_password);
        checkPassword = (TextInputLayout) findViewById(R.id.check_password);
        if (checkPassword != null) {
            checkPassword.setErrorEnabled(true);
        }

        copyPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(copyPassword.getText().toString().equals(password.getText().toString())){
                    checkPassword.setError("");
                }else {
                    checkPassword.setError("Неверно");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        regButton = (Button) findViewById(R.id.registration_complete);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((lastName.getText().length() != 0) && (firstName.getText().length() !=0) && (email.getText().length() != 0) && (password.getText().length() !=0) && (copyPassword.getText().length() != 0)){
                    if (email.getText().toString().matches(Patterns.EMAIL_ADDRESS.toString())){
                        RegistrationTask task = new RegistrationTask(StudentRegistrationActivity.this,
                                getApplicationContext(),
                                firstName.getText().toString() +" "+lastName.getText().toString(),
                                spinner.getSelectedItem().toString(),
                                email.getText().toString(),
                                password.getText().toString());
                        task.execute();
                    }else {
                        Toast.makeText(getApplicationContext(), "Некорректный e-mail", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
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
            getSupportActionBar().setTitle("Регистрация студента");
        }
    }
}
