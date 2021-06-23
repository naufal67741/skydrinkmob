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

import java.text.SimpleDateFormat;
import java.util.Vector;

public class EditProfileActivity extends AppCompatActivity {
    private EditText id_edit_regis_email;
    private EditText id_edit_regis_password;
    private EditText id_edit_regis_confirm_new_password;
    private EditText id_edit_regis_new_password;
    private EditText id_edit_regis_phone_number;
    private RadioGroup id_edit_regis_radio;
    private RadioButton id_edit_radio_male;
    private RadioButton id_edit_radio_female;
    private Button btn_edit;
    String regis_email;
    String regis_password;
    String regis_confirm_new_password;
    String regis_new_password;
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
        setContentView(R.layout.activity_edit_profile);

        id_edit_regis_email = findViewById(R.id.id_edit_regis_email);
        id_edit_regis_email.setText(LoginActivity.login_user.getUserEmail());

        id_edit_regis_password = findViewById(R.id.id_edit_regis_password);
        id_edit_regis_confirm_new_password = findViewById(R.id.id_edit_regis_confirm_new_password);
        id_edit_regis_new_password = findViewById(R.id.id_edit_regis_new_password);

        id_edit_regis_phone_number = findViewById(R.id.id_edit_regis_phone_number);
        id_edit_regis_phone_number.setText(LoginActivity.login_user.getUserPhoneNumber());

        id_edit_regis_radio = findViewById(R.id.id_edit_regis_radio);
        id_edit_radio_male = findViewById(R.id.id_edit_radio_male);
        id_edit_radio_female = findViewById(R.id.id_edit_radio_female);
        if (LoginActivity.login_user.getUserGender().equals("Male")) {
            id_edit_radio_male.setChecked(true);
        } else if(LoginActivity.login_user.getUserGender().equals("Female")) {
            id_edit_radio_female.setChecked(true);
        }
        btn_edit = findViewById(R.id.btn_edit);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regis_email = id_edit_regis_email.getText().toString();
                regis_password = id_edit_regis_password.getText().toString();
                regis_confirm_new_password = id_edit_regis_confirm_new_password.getText().toString();
                regis_new_password = id_edit_regis_new_password.getText().toString();
                regis_phone_number = id_edit_regis_phone_number.getText().toString();

                selected_radio_gender = (RadioButton) findViewById(id_edit_regis_radio.getCheckedRadioButtonId());
                regis_gender = selected_radio_gender.getText().toString();

                if (regis_email.length() == 0 || regis_password.length() == 0 || regis_new_password.length() == 0 ||
                        regis_confirm_new_password.length() == 0 || regis_phone_number.length() == 0) {
                    Toast.makeText(EditProfileActivity.this, "Please fill all form", Toast.LENGTH_LONG).show();
                } else if (!regis_password.equals(LoginActivity.login_user.getUserPassword())) {
                    Toast.makeText(EditProfileActivity.this, "Old password wrong", Toast.LENGTH_LONG).show();
                } else if (regis_new_password.length() < 9) {
                    Toast.makeText(EditProfileActivity.this, "Password min 9 digits", Toast.LENGTH_LONG).show();
                } else if (!regis_new_password.equals(regis_confirm_new_password)) {
                    Toast.makeText(EditProfileActivity.this, "Password confirmation doesnt match", Toast.LENGTH_LONG).show();
                } else if (regis_email.indexOf("@") == -1 || regis_email.indexOf(".") == -1) {
                    Toast.makeText(EditProfileActivity.this, "Email must contain @ and .", Toast.LENGTH_LONG).show();
                } else if (regis_email.indexOf("@") - regis_email.indexOf(".") == 1 || regis_email.indexOf("@") - regis_email.indexOf(".") == -1) {
                    Toast.makeText(EditProfileActivity.this, "@ and . cant be next to each other", Toast.LENGTH_LONG).show();
                } else if (isCharacterOccuredOnlyOnce('@', regis_email) == false) {
                    Toast.makeText(EditProfileActivity.this, "can only contain one @", Toast.LENGTH_LONG).show();
                } else if (regis_password.length() < 9) {
                    Toast.makeText(EditProfileActivity.this, "Password min 9 digits", Toast.LENGTH_LONG).show();
                } else if (regis_phone_number.length() > 13) {
                    Toast.makeText(EditProfileActivity.this, "Phone number max 13 digits", Toast.LENGTH_LONG).show();
                } else if (regis_phone_number.charAt(0) != '6' || regis_phone_number.charAt(1) != '2') {
                    Toast.makeText(EditProfileActivity.this, "Phone number must start with 62", Toast.LENGTH_LONG).show();
                } else if (selected_radio_gender.isChecked() == false) {
                    Toast.makeText(EditProfileActivity.this, "You have to choose your gender !", Toast.LENGTH_LONG).show();
                }else if(isAlpha(regis_new_password) == 0 || isNumeric(regis_new_password) == 0){
                    Toast.makeText(EditProfileActivity.this, "Password must be Alphanumeric", Toast.LENGTH_LONG).show();
                }else {
                    LoginActivity.login_user.setUserEmail(regis_email);
                    LoginActivity.login_user.setUserGender(regis_gender);
                    LoginActivity.login_user.setUserPassword(regis_new_password);
                    LoginActivity.login_user.setUserPhoneNumber(regis_phone_number);
//                    users.add(new User(regis_email, regis_phone_number, regis_password, regis_gender, regis_dob));
                    MyDB.updateUserData(LoginActivity.login_user.getUserID(),regis_email,regis_new_password,regis_gender,regis_phone_number,EditProfileActivity.this);
                    Intent i = new Intent(EditProfileActivity.this, HomeActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}