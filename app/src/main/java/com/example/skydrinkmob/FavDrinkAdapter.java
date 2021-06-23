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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.skydrinkmob.db.MyDB;
import com.example.skydrinkmob.model.Drink;
import com.example.skydrinkmob.model.User;

import java.util.Vector;

public class FavDrinkAdapter extends ArrayAdapter<Drink> {
    public static Vector<Drink> musics = new Vector<>();
    private Context ctx;
    private int res;
    private EditText id_row_qty;
    int row_qty;

    public FavDrinkAdapter(@NonNull Context context, int resource, @NonNull Vector<Drink> musics) {
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
        TextView tv_name = convertView.findViewById(R.id.favrow_drink_name);
        TextView tv_desc = convertView.findViewById(R.id.favrow_drink_desc);
        TextView tv_price = convertView.findViewById(R.id.favrow_price);
        TextView tv_sweetness = convertView.findViewById(R.id.favrow_drink_sweetness);

        Button favrow_button = convertView.findViewById(R.id.favrow_button);

//        favrow_qty = Integer.parseInt(id_favrow_qty);
        final Drink drink = MyDB.vFavDrinks.get(position);
        final User user = LoginActivity.login_user;

//        imageView.setImageResource(getItem(position).getDrinkImage());
        tv_name.setText(getItem(position).getDrinkName());
        tv_desc.setText(getItem(position).getDrinkDescription());
//        tv_sweetness.setText(""+getItem(position).getDrinkSweetness()+" - "+getItem(position).getDrinkSpicy()+" - "+getItem(position).getDrinkMalty());
        tv_sweetness.setText("Sweetness : "+getItem(position).getDrinkSweetness()+" | Spicy : "+getItem(position).getDrinkSpicy()+" | Malty : "+getItem(position).getDrinkMalty());
        tv_price.setText("$" + getItem(position).getDrinkPrice());


        favrow_button.setOnClickListener(new View.OnClickListener() {
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
//                                    MyDB.vTransactions.add(new Transaction(""+MyDB.vTransactions.size()+1,LoginActivity.login_user.getUserEmail(), music.getTitle()));
//                                    Date c = Calendar.getInstance().getTime();
//                                    System.out.println("Current time => " + c);
//
//                                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//                                    String formattedDate = df.format(c);
//                                    String transactionDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                    MyDB.insertCartData(LoginActivity.login_user.getUserID(), drink.getDrinkID(),drink.getDrinkPrice(), ctx);
                                    Toast.makeText(ctx, "Transaction Success", Toast.LENGTH_LONG).show();
//                                    Toast.makeText(ctx, MyDB.vTransactions.get(0).getTitle(), Toast.LENGTH_LONG).show();
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
        return convertView;
    }
}
