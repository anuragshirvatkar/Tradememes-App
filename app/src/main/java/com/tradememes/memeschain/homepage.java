package com.tradememes.memeschain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;


import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class homepage extends AppCompatActivity {

    public CircleImageView b1;
    RecyclerView recycle;
    List<Model> prolist;
    MyAdapter myadapter;
    CircleImageView pf,of;
    String getPi;
    DatabaseReference acc,acc3;
    String final1;
    private DatabaseReference refrence;
    private ValueEventListener eventListener;
    String date,date2;
    Toolbar t1;
ProgressBar p1;
 RewardedAd mRewardedAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_homepage);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        pf = findViewById(R.id.toolphoto);
      t1 = (Toolbar) findViewById(R.id.toolbar1);
      setSupportActionBar(t1);
        acc = FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid());
        date2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        acc3 = FirebaseDatabase.getInstance().getReference().child("counter"+date2).child(FirebaseAuth.getInstance().getUid()).child("0");
          p1 = (ProgressBar) findViewById(R.id.progressBar) ;
checkConnection();
            acc.child("url").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    final1 = snapshot.getValue().toString();
                    Glide.with(homepage.this).load(final1).into(pf);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            acc3.setValue("0");



            pf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConnectivityManager manager2 = (ConnectivityManager)
                            getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork2 = manager2.getActiveNetworkInfo();
                    if(null!=activeNetwork2){
                        startActivity(new Intent(getApplicationContext(), MainActivity2.class));

                    }else{
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(homepage.this);
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

            Toolbar toolbar = findViewById(R.id.toolbar1);
            setSupportActionBar(toolbar);
            recycle = (RecyclerView) findViewById(R.id.recycler1);
            LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
            recycle.setLayoutManager(gridLayoutManager);
            gridLayoutManager.setReverseLayout(true);
            gridLayoutManager.setStackFromEnd(true);
            prolist = new ArrayList<>();
            myadapter = new MyAdapter(homepage.this, prolist);
            recycle.setAdapter(myadapter);
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            refrence = FirebaseDatabase.getInstance().getReference(date);

            eventListener = refrence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    prolist.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        Model dete = itemSnapshot.getValue(Model.class);
                        prolist.add(dete);
                        p1.setVisibility(View.INVISIBLE);
                    }
                    myadapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            b1 = (CircleImageView) findViewById(R.id.floatingActionButton);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mRewardedAd != null) {
                        Activity activityContext = homepage.this;
                        mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                // Handle the reward.
                                Log.d("TAG", "The user earned the reward.");
                                int rewardAmount = rewardItem.getAmount();
                                String rewardType = rewardItem.getType();
                            }
                        });
                    } else {
                        Log.d("TAG", "The rewarded ad wasn't ready yet.");
                    }

                    startActivity(new Intent(getApplicationContext(), uploadmeme.class));
                }
            });
        }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(homepage.this);
        alertDialog.setTitle("Exit"); // Sets title for your alertbox
        alertDialog.setMessage("Do you really want to exit app ?"); // Message to be displayed on alertbox
        /* When positive (yes/ok) is clicked */

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        /* When negative (No/cancel) button is clicked*/
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();

    }
public void checkConnection(){


        ConnectivityManager manager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if(null!=activeNetwork){


        }
        else{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(homepage.this);
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

    AdRequest adRequest = new AdRequest.Builder().build();

    RewardedAd.load(this, "ca-app-pub-1086704177750756/2764706703",
            adRequest, new RewardedAdLoadCallback() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    // Handle the error.
                    Log.d("TAG", loadAdError.getMessage());
                    mRewardedAd = null;
                }

                @Override
                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                    mRewardedAd = rewardedAd;
                    Log.d("TAG", "Ad was loaded.");
                }
            });

}


}


