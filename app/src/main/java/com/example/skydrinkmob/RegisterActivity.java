package com.example.skydrinkmob;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skydrinkmob.db.MyDB;
import com.example.skydrinkmob.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {
    public static User tempRegisterUser = new User();
    public static Vector<User> users = new Vector<>();
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat;

    private EditText id_regis_email;
    private EditText id_regis_password;
    private EditText id_regis_confirm_password;
    private EditText id_regis_phone_number;
    private RadioGroup id_regis_radio;
    private RadioButton id_radio_male;
    private RadioButton id_radio_female;
    private Button btn_regis;

    private CheckBox id_regis_tos;
    String regis_email;
    String regis_password;
    String regis_confirm_password;
    String regis_phone_number;
    RadioButton selected_radio_gender;
    String regis_gender;

    private boolean isCharacterOccuredOnlyOnce(char letter, String str) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == letter) {
                counter++;
            }
        }
        if (counter == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static int isAlpha(String str){
        int alphaCounter = 0;
        int numCounter = 0;

        for(int i=0;i<str.length();i++){
            char karakter = str.charAt(i);
            if(Character.isAlphabetic(karakter)){
                alphaCounter++;
            }
        }
        return alphaCounter;
    }

    public static int isNumeric(String str){
        int numCounter = 0;

        for(int i=0;i<str.length();i++){
            char karakter = str.charAt(i);
            if(Character.isDigit(karakter)){
                numCounter++;
            }
        }
        return numCounter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        id_regis_email = findViewById(R.id.id_regis_email);

        id_regis_password = findViewById(R.id.id_regis_password);
        id_regis_confirm_password = findViewById(R.id.id_regis_confirm_password);
        id_regis_phone_number = findViewById(R.id.id_regis_phone_number);
        id_regis_radio = findViewById(R.id.id_regis_radio);
        id_radio_male = findViewById(R.id.id_radio_male);
        id_radio_female = findViewById(R.id.id_radio_female);
        btn_regis =  findViewById(R.id.btn_regis);
        id_regis_tos = findViewById(R.id.id_regis_tos);

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regis_email = id_regis_email.getText().toString();
                regis_password = id_regis_password.getText().toString();
                regis_confirm_password = id_regis_confirm_password.getText().toString();
                regis_phone_number = id_regis_phone_number.getText().toString();

                selected_radio_gender = (RadioButton) findViewById(id_regis_radio.getCheckedRadioButtonId());
                regis_gender = selected_radio_gender.getText().toString();

                if (regis_email.length() == 0 || regis_password.length() == 0 || regis_confirm_password.length() == 0 || regis_phone_number.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Please fill all form", Toast.LENGTH_LONG).show();
                } else if (regis_email.indexOf("@") == -1 || regis_email.indexOf(".") == -1) {
                    Toast.makeText(RegisterActivity.this, "Email must contain @ and .", Toast.LENGTH_LONG).show();
                } else if (regis_email.indexOf("@") - regis_email.indexOf(".") == 1 || regis_email.indexOf("@") - regis_email.indexOf(".") == -1) {
                    Toast.makeText(RegisterActivity.this, "@ and . cant be next to each other", Toast.LENGTH_LONG).show();
                } else if (isCharacterOccuredOnlyOnce('@', regis_email) == false) {
                    Toast.makeText(RegisterActivity.this, "can only contain one @", Toast.LENGTH_LONG).show();
                } else if (regis_password.length() < 9) {
                    Toast.makeText(RegisterActivity.this, "Password min 9 digits", Toast.LENGTH_LONG).show();
                } else if (regis_phone_number.length() > 13) {
                    Toast.makeText(RegisterActivity.this, "Phone number max 13 digits", Toast.LENGTH_LONG).show();
                } else if (regis_phone_number.charAt(0) != '6' || regis_phone_number.charAt(1) != '2') {
                    Toast.makeText(RegisterActivity.this, "Phone number must start with 62", Toast.LENGTH_LONG).show();
                } else if (regis_password.equals(regis_confirm_password) == false) {
                    Toast.makeText(RegisterActivity.this, "Password confirmation doesnt match", Toast.LENGTH_LONG).show();
                } else if (id_regis_tos.isChecked() == false) {
                    Toast.makeText(RegisterActivity.this, "You must agree with TOS", Toast.LENGTH_LONG).show();
                } else if (selected_radio_gender.isChecked() == false) {
                    Toast.makeText(RegisterActivity.this, "You have to choose your gender !", Toast.LENGTH_LONG).show();
                }else if(isAlpha(regis_password) == 0 || isNumeric(regis_password) == 0){
                    Toast.makeText(RegisterActivity.this, "Password must be Alphanumeric", Toast.LENGTH_LONG).show();
                }
                else {
//                    users.add(new User(regis_email, regis_phone_number, regis_password, regis_gender, regis_dob));

                    MyDB.insertUserData(regis_email,regis_password,regis_gender,regis_phone_number, 0,RegisterActivity.this);

                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        });
    }
    public void goToLoginPage(View view) {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
    }
}