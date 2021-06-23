package com.example.skydrinkmob;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skydrinkmob.db.MyDB;
import com.example.skydrinkmob.model.Drink;

import java.util.Vector;

public class FavDrinkList extends AppCompatActivity {
    ListView listView;
    public static Vector<Drink> drinks = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_activity_drink_list);
//        TextView tvtest = (TextView) findViewById(R.id.testtvmusic);
//        tvtest.setText(HomeActivity.musics.get(0).getTitle());
        listView = findViewById(R.id.id_fav_listview);
        FavDrinkAdapter favDrinkAdapter = new FavDrinkAdapter(this, R.layout.fav_row_drink_list, MyDB.vFavDrinks);
        listView.setAdapter(favDrinkAdapter);

//
    }
}