package com.example.skydrinkmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skydrinkmob.model.Drink;

import java.util.Vector;

public class MyDrinkListActivity extends AppCompatActivity {
    ListView listView;
//    private void initializeMyMusic(){
//        Music drink;
//        for(int i = 0;i<transactions.size();i++){
//            if(transactions.get(i).getUser_email() == LoginActivity.login_user.getUser_email()){
//                for(int j = 0;j<HomeActivity.drinks.size();j++){
//                    if(transactions.get(i).getTitle() == HomeActivity.drinks.get(j).getTitle()){
//                        drink = HomeActivity.drinks.get(j);
//                        HomeActivity.my_drinks.add(drink);
//                    }
//                }
//            }
//        }
//    }

//    private void initializeMyMusic() {
//        Vector<Transaction> vTrx = new Vector<>();
//        Transaction trx;
//        Music drinkTemp;
//        HomeActivity.my_drinks.clear();
//        for (int i = 0; i < transactions.size(); i++) {
//            if (transactions.get(i).getUser_email() == LoginActivity.login_user.getUser_email()) {
//                trx = transactions.get(i);
//                vTrx.add(trx);
//            }
//        }
//        for(int i=0;i<vTrx.size();i++){
//            if(vTrx.get(i).getTitle() == HomeActivity.drinks.get(i).getTitle()){
//                drinkTemp = HomeActivity.drinks.get(i);
//                HomeActivity.my_drinks.add(drinkTemp);
//            }
//        }
//    }

    public static Vector<Drink> drinks = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_drink_list);
        getSupportActionBar().setTitle("BANG - My Drink");
//        initializeMyDrink();
        listView = findViewById(R.id.id_my_listview);
        Vector<Drink> myDrink = HomeActivity.searchDrinkByUserID(LoginActivity.login_user.getUserID());
        MyDrinkListAdapter myDrinkAdapter = new MyDrinkListAdapter(this, R.layout.my_row_drink_list, myDrink);
        listView.setAdapter(myDrinkAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.checkout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_add_menu:
                Intent i = new Intent(MyDrinkListActivity.this, CheckoutActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}