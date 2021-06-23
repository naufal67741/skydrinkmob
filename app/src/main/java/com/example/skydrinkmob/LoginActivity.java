package com.example.skydrinkmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skydrinkmob.db.MyDB;
import com.example.skydrinkmob.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText id_login_email;
    private EditText id_login_password;
    private Button btn_login;
    public static User login_user;
    String login_email;
    String login_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.id_btn_login);
        id_login_email = findViewById(R.id.id_login_email);
        id_login_password = findViewById(R.id.id_login_password);

        if(MyDB.isDataInserted == false){
            MyDB.initializeMusic(LoginActivity.this);
        }

        Log.d("masukGakDrink", ""+MyDB.vDrinks.size());

        MyDB.loadUserData(LoginActivity.this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_email = id_login_email.getText().toString();
                login_password = id_login_password.getText().toString();

                if (login_email.length() == 0 || login_password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Please fill all form", Toast.LENGTH_LONG).show();
                } else{
                    User foundUser = null;
                    for(User user: MyDB.vUsers){
                        if(user.getUserEmail().equals(login_email) && user.getUserPassword().equals(login_password)){
                            foundUser = user;
                        }
                    }
                    if(foundUser == null){
                        Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_LONG).show();
                    }else{
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        login_user = foundUser;
                        startActivity(i);
                    }
                }
            }
        });
    }
    public void goToRegisterPage(View view) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
}