package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**

 This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
int jumlah = 2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**

     This method is called when the order button is clicked.
     */
    public String createOrderSummary(String Name, int jumlah , int total, boolean haswhippedcream,boolean hasChocolate){
        return "name = "+Name+" \n quantity = "+jumlah+"\n apakah membutuhkan Whipped Cream = "+haswhippedcream+ "\n Apakah butuh chocolate = "+hasChocolate+"\n Total = $"+total+"\n Thank YOUUUU!!" ;
    }
    public int calculatePrice(){
        int price = jumlah*5;
        return price;

    }
    public void submitOrder(View view) {
        int price = calculatePrice() ;
    CheckBox getChocolate = (CheckBox) findViewById(R.id.cb_chocolate) ;
    boolean hasChocolate = getChocolate.isChecked();
    CheckBox getwhippedcream = (CheckBox) findViewById(R.id.cb_whipped);
    boolean haswhippedcream = getwhippedcream.isChecked();
    EditText getName = (EditText) findViewById(R.id.et_name);
    String getNamefrom = getName.getText().toString();



    if (hasChocolate== true) {
        price= price + 2*jumlah;
    }

    if (haswhippedcream==true){
        price = price + 1*jumlah;
    }

        String message= createOrderSummary(getNamefrom,jumlah, price,haswhippedcream,hasChocolate);
        displayMessage(message);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "UNTUK BAPAK "+getNamefrom);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void tambahjumlah(View view){

        jumlah= jumlah +1;
        if(jumlah>5){
            jumlah=5;
        }
        display(jumlah);
    }
    public void kurangjumlah(View view){
        jumlah = jumlah-1;
        if(jumlah< 1) {
        jumlah=1;
        }
        display(jumlah);

    }

    private void display(int number) {
        TextView jumlah_tv = (TextView) findViewById(
                R.id.tv_jumlah);
        jumlah_tv.setText("" + number);
    }


    public void displayMessage(String message){
        TextView tv_message = (TextView) findViewById(R.id.tv_message);
        tv_message.setText(message);
    }
}

