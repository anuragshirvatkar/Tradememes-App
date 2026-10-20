package com.tradememes.memeschain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class splash extends AppCompatActivity {
    Button move, reg;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        t1 = findViewById(R.id.textView4);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager manager = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
                if(null!=activeNetwork){
                    Intent intent = new Intent(splash.this, login.class);
                    startActivity(intent);

                }
                else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(splash.this);
                    alertDialog.setTitle("Network Error"); // Sets title for your alertbox
                    alertDialog.setMessage("You are not connected to internet"); // Message to be displayed on alertbox
                    /* When positive (yes/ok) is clicked */

                    alertDialog.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            finish();
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    });
                    /* When negative (No/cancel) button is clicked*/
                    alertDialog.setNegativeButton("Exit App", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
                    alertDialog.show();


                }



            }
        });
        move = findViewById(R.id.button13);
        reg = findViewById(R.id.button14);
        move.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view)
                                    {


                                    }


                                }
        );

        reg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                ConnectivityManager manager = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
                if(null!=activeNetwork){
                    Intent intent=new Intent(splash.this,
                            reg.class);
                    startActivity(intent);

                }
                else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(splash.this);
                    alertDialog.setTitle("Network Error"); // Sets title for your alertbox
                    alertDialog.setMessage("You are not connected to internet"); // Message to be displayed on alertbox
                    /* When positive (yes/ok) is clicked */

                    alertDialog.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            finish();
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    });
                    /* When negative (No/cancel) button is clicked*/
                    alertDialog.setNegativeButton("Exit App", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
                    alertDialog.show();


                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void checkConnection(){


        ConnectivityManager manager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if(null!=activeNetwork){


        }
        else{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(splash.this);
            alertDialog.setTitle("Network Error"); // Sets title for your alertbox
            alertDialog.setMessage("You are not connected to internet"); // Message to be displayed on alertbox
            /* When positive (yes/ok) is clicked */

            alertDialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    finish();

                }
            });
            /* When negative (No/cancel) button is clicked*/
            alertDialog.setNegativeButton("Exit App", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            alertDialog.show();


        }
    }


}