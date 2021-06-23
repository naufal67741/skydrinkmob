package com.example.skydrinkmob;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.skydrinkmob.db.MyDB;
import com.example.skydrinkmob.model.Cart;
import com.example.skydrinkmob.model.User;
import com.example.skydrinkmob.model.Drink;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

public class DrinkAdapter extends ArrayAdapter<Drink> {
    public static Vector<Drink> musics = new Vector<>();
    private Context ctx;
    private int res;
    private EditText id_row_qty;
    int row_qty;

    public DrinkAdapter(@NonNull Context context, int resource, @NonNull Vector<Drink> musics) {
        super(context, resource, musics);
        this.ctx = context;
        this.res = resource;
    }

    public boolean isBought(Drink music){
        boolean isBought = false;
        for (int i = 0; i < MyDB.vCarts.size(); i++) {
            if (music.getDrinkID() == MyDB.vCarts.get(i).getDrinkID()
                    && LoginActivity.login_user.getUserID() == MyDB.vCarts.get(i).getUserID()) {
                isBought = true;
            }
        }
        return isBought;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        convertView = layoutInflater.inflate(res, parent, false);
        TextView tv_name = convertView.findViewById(R.id.row_drink_name);
        TextView tv_desc = convertView.findViewById(R.id.row_drink_desc);
        TextView tv_price = convertView.findViewById(R.id.row_price);
        TextView tv_sweetness = convertView.findViewById(R.id.row_drink_sweetness);

        Button row_button = convertView.findViewById(R.id.row_button);
        Button row_fav = convertView.findViewById(R.id.row_fav);

//        row_qty = Integer.parseInt(id_row_qty);
        final Drink drink = MyDB.vDrinks.get(position);
        final User user = LoginActivity.login_user;

//        imageView.setImageResource(getItem(position).getDrinkImage());
        tv_name.setText(getItem(position).getDrinkName());
        tv_desc.setText(getItem(position).getDrinkDescription());
        tv_sweetness.setText("Sweetness : "+getItem(position).getDrinkSweetness()+" | Spicy : "+getItem(position).getDrinkSpicy()+" | Malty : "+getItem(position).getDrinkMalty());
        tv_price.setText("$" + getItem(position).getDrinkPrice());

        row_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ctx, music.getTitle(), Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
                alertDialogBuilder.setTitle("Are you sure wanna buy this ?");
                alertDialogBuilder
                        .setMessage(drink.getDrinkName())
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (isBought(drink) == true) {
                                    Toast.makeText(ctx, "You already bought this drink !", Toast.LENGTH_LONG).show();
                                } else {

                                    MyDB.insertCartData(LoginActivity.login_user.getUserID(), drink.getDrinkID(),drink.getDrinkPrice(), ctx);
                                    Toast.makeText(ctx, "Transaction Success", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(ctx, HomeActivity.class);
                                    ctx.startActivity(i);
                                }
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

        row_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
                alertDialogBuilder.setTitle("Are you sure this is your favorite ?");
                alertDialogBuilder
                        .setMessage(drink.getDrinkName())
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                    MyDB.vTransactions.add(new Transaction(""+MyDB.vTransactions.size()+1,LoginActivity.login_user.getUserEmail(), music.getTitle()));
//                                    Date c = Calendar.getInstance().getTime();
//                                    System.out.println("Current time => " + c);
//
//                                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//                                    String formattedDate = df.format(c);
//                                    String transactionDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//                                    MyDB.insertCartData(LoginActivity.login_user.getUserID(), drink.getDrinkID(),drink.getDrinkPrice(), ctx);
                                    MyDB.updateUserCluster(LoginActivity.login_user.getUserID(), drink.getClusterID(),ctx);
                                    Toast.makeText(ctx, "Success add favorite", Toast.LENGTH_LONG).show();
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
