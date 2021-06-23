package com.example.skydrinkmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skydrinkmob.db.MyDB;
import com.example.skydrinkmob.model.Cart;
import com.example.skydrinkmob.model.Drink;
import com.example.skydrinkmob.model.Transaction;

import java.util.Vector;

public class HomeActivity extends AppCompatActivity {
    public static Vector<Drink> searchDrinkByUserID(int search_id){
        Vector<Drink> tempDrink = new Vector<>();
        Vector<Cart> tempVectorIndex = new Vector<>();
        for(int i=0;i<MyDB.vCarts.size();i++){
            if(MyDB.vCarts.get(i).getUserID() == search_id){
                tempVectorIndex.add(MyDB.vCarts.get(i));
            }
        }
        for(int i=0;i<tempVectorIndex.size();i++){
            for(int j = 0;j<MyDB.vDrinks.size();j++){
                if(tempVectorIndex.get(i).getDrinkID() == MyDB.vDrinks.get(j).getDrinkID()){
                    tempDrink.add(MyDB.vDrinks.get(j));
                    break;
                }
            }
        }
        return tempDrink;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        MyDB.loadDrinkData(HomeActivity.this);
        Log.d("userID", ""+ LoginActivity.login_user.getUserID());
        MyDB.loadCartData(LoginActivity.login_user.getUserID(),HomeActivity.this);
        MyDB.loadFavDrinkData(HomeActivity.this);
//        MyDB.loadTransactionData(HomeActivity.this);
        Log.d("sizeCart", ""+ MyDB.vCarts.size());
        Log.d("usercluster", ""+ LoginActivity.login_user.getClusterID());
        TextView tv_home = findViewById(R.id.tv_home);
        tv_home.setText("Hello "+LoginActivity.login_user.getUserEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_drink_list:
                Intent i = new Intent(HomeActivity.this, DrinkList.class);
                startActivity(i);
                break;
            case R.id.id_menu_my_drink:
                if (searchDrinkByUserID(LoginActivity.login_user.getUserID()).isEmpty()) {
                    Toast.makeText(HomeActivity.this, "You dont have any drink !", Toast.LENGTH_LONG).show();
                } else {
                    Intent j = new Intent(HomeActivity.this, MyDrinkListActivity.class);
                    startActivity(j);
                }
                break;

            case R.id.id_menu_profile:
                Intent k = new Intent(HomeActivity.this, EditProfileActivity.class);
                startActivity(k);
                break;
            case R.id.id_menu_recomendation:
                if (LoginActivity.login_user.getClusterID() == 0) {
                    Toast.makeText(HomeActivity.this, "You dont have any favorite drink !", Toast.LENGTH_LONG).show();
                } else {
                    Intent e = new Intent(HomeActivity.this, FavDrinkList.class);
                    startActivity(e);
                }
                break;
            case R.id.id_menu_logout:
                Intent m = new Intent(HomeActivity.this, LoginActivity.class);
                LoginActivity.login_user = null;
                m.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}