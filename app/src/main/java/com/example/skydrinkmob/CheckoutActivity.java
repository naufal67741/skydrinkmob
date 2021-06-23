package com.example.skydrinkmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skydrinkmob.db.MyDB;
import com.example.skydrinkmob.model.Cart;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        final String transactionDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        int totalPrice = 0;
        for(Cart cart : MyDB.vCarts){
            totalPrice = totalPrice + cart.getSubTotal();
        }

        final TextView id_checkout_totalprice = findViewById(R.id.id_checkout_totalprice);
        Button btn_checkout = findViewById(R.id.btn_checkout);
//        int lastIndex = MyDB.vTransactions.size();
        id_checkout_totalprice.setText("Your total price is : $"+totalPrice);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sizeTrxSblmInsert", ""+ MyDB.vTransactions.size());
                MyDB.insertTransactionData(transactionDate,LoginActivity.login_user.getUserID(),CheckoutActivity.this);
                Log.d("sizeTrxStlhInsert", ""+ MyDB.vTransactions.size());

                Log.d("sizeTrxStlhLoad", ""+ MyDB.vTransactions.size());
                MyDB.deleteCartByUserID(LoginActivity.login_user.getUserID(),CheckoutActivity.this);
                Toast.makeText(CheckoutActivity.this, "Checkout success!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(CheckoutActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }

    public void goToDrinkPage(View view) {
        Intent i = new Intent(CheckoutActivity.this, DrinkList.class);
        startActivity(i);
    }
}