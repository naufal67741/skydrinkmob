package com.example.skydrinkmob;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.skydrinkmob.model.Drink;
import com.example.skydrinkmob.model.User;
import com.example.skydrinkmob.db.MyDB;
import com.example.skydrinkmob.model.Drink;
import com.example.skydrinkmob.model.User;

import java.util.Vector;

public class MyDrinkListAdapter extends ArrayAdapter<Drink> {
    public static Vector<Drink> drinks = new Vector<>();
    public static Vector<Drink> my_drinks = new Vector<>();
    private Context ctx;
    private int res;



    public MyDrinkListAdapter(@NonNull Context context, int resource, @NonNull Vector<Drink> my_drinks) {
        super(context, resource, my_drinks);
        this.ctx = context;
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        convertView = layoutInflater.inflate(res, parent, false);
        TextView tv_name = convertView.findViewById(R.id.my_row_drink_name);
        TextView tv_desc = convertView.findViewById(R.id.my_row_drink_desc);
        TextView tv_price = convertView.findViewById(R.id.my_row_price);
        TextView tv_sweetness = convertView.findViewById(R.id.my_row_drink_sweetness);
        Button row_button = convertView.findViewById(R.id.my_row_button);

        final Drink drink = HomeActivity.searchDrinkByUserID(LoginActivity.login_user.getUserID()).get(position);
        final User user = LoginActivity.login_user;

//        imageView.setImageResource(getItem(position).getDrinkImage());
        tv_name.setText(getItem(position).getDrinkName());
        tv_desc.setText(getItem(position).getDrinkDescription());
        tv_sweetness.setText(""+getItem(position).getDrinkSweetness()+" - "+getItem(position).getDrinkSpicy()+" - "+getItem(position).getDrinkMalty());
        tv_sweetness.setText("Sweetness : "+getItem(position).getDrinkSweetness()+" | Spicy : "+getItem(position).getDrinkSpicy()+" | Malty : "+getItem(position).getDrinkMalty());
        tv_price.setText("$" + getItem(position).getDrinkPrice());

        row_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ctx, music.getTitle(), Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
                alertDialogBuilder.setTitle("Are you sure wanna cancel this ?");
                alertDialogBuilder
                        .setMessage(drink.getDrinkName())
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                    MyDB.deleteCartData(LoginActivity.login_user.getUserID(), drink.getDrinkID(), ctx);
                                    Toast.makeText(ctx, "Delete Success", Toast.LENGTH_LONG).show();
//                                    Toast.makeText(ctx, MyDB.vTransactions.get(0).getTitle(), Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(ctx, HomeActivity.class);
                                    ctx.startActivity(i);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        return convertView;
    }
}
