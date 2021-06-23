package com.example.skydrinkmob;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skydrinkmob.db.MyDB;
import com.example.skydrinkmob.model.Drink;


import java.util.Vector;

public class DrinkList extends AppCompatActivity {
    ListView listView;
    public static Vector<Drink> drinks = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list);
//        TextView tvtest = (TextView) findViewById(R.id.testtvmusic);
//        tvtest.setText(HomeActivity.musics.get(0).getTitle());
        listView = findViewById(R.id.id_listview);
        DrinkAdapter drinkAdapter = new DrinkAdapter(this, R.layout.row_drink_list, MyDB.vDrinks);
        listView.setAdapter(drinkAdapter);

//
    }
}