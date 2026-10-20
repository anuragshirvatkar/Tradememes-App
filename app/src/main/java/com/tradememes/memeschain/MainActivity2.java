package com.tradememes.memeschain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity2 extends AppCompatActivity {
CircleImageView c1;
Button b1,b2,b3,b4,b5;
FirebaseAuth f2;
String id;
FirebaseAuth f1;
TextView name,mail;
String dfc;
    public DatabaseReference acc,acc9,reference2;
RewardedAd mRewardedAd;
    String final1;
    private static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        b1=findViewById(R.id.button5);
        b2=findViewById(R.id.button7);
        b3=findViewById(R.id.button8);

        b5=findViewById(R.id.button10);
        name=findViewById(R.id.textView19);
        mail=findViewById(R.id.textView21);
        f2=FirebaseAuth.getInstance();
        f1=FirebaseAuth.getInstance();
       acc9= FirebaseDatabase.getInstance().getReference().child("users");


        acc9.child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users profile = snapshot.getValue(users.class);
                if(profile != null)
                { String n1 = profile.name;
String n2 = profile.fix;
                   mail.setText(n2);
                   name.setText(n1);
                } }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        acc = FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid());




        acc.child("url").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final1 = snapshot.getValue().toString();
                Glide.with(MainActivity2.this).load(final1).into(c1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), profile.class));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), homepage.class));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), rules.class));
            }
        });



        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openDialog();

            }
        });


        Intent intent = getIntent();
        String value = intent.getStringExtra("key");
        c1=findViewById(R.id.mainpf);
        Glide.with(this).load(value).into(c1);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), profile.class));

            }
        });



    }
   public void openDialog()
   {

       AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity2.this);

       alertDialog.setTitle("Logout"); // Sets title for your alertbox

       alertDialog.setMessage("Are you sure you want to Logout ?"); // Message to be displayed on alertbox



       /* When positive (yes/ok) is clicked */
       alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog,int which) {
               finish();
               f1.signOut();
               Intent intent=new Intent(MainActivity2.this,login.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);
               finish();
               Toast.makeText(MainActivity2.this,"Successfully Logged Out", Toast.LENGTH_LONG).show();
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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity2.this,homepage.class);
        startActivity(intent);
    }


   }
