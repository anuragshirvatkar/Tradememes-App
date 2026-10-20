package com.tradememes.memeschain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {

    private CircleImageView c1;
    public DatabaseReference acc;
    Button save;
Toolbar toolbar;
    DatabaseReference acc2;
    private String userId;
    FirebaseAuth au;
    FirebaseUser vapar;
    EditText e1,e2;
ProgressBar p5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = findViewById(R.id.t2);
        setSupportActionBar(toolbar);
        p5=findViewById(R.id.progressBar5);
        e1 = (EditText) findViewById(R.id.editTextTextPersonName2);
        e2 = (EditText) findViewById(R.id.editTextTextPersonName3);
        acc = FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid());
        c1 = (CircleImageView) findViewById(R.id.imagemain);
        save = findViewById (R.id.button11);
        vapar = FirebaseAuth.getInstance().getCurrentUser();
        acc2 = FirebaseDatabase.getInstance().getReference().child("users");
        userId = vapar.getUid();
        au = FirebaseAuth.getInstance();
        acc2 = FirebaseDatabase.getInstance().getReference().child("users");

acc.child("url").addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
String final1 = snapshot.getValue().toString();
        Glide.with(profile.this).load(final1).into(c1);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});



     c1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent=new Intent(profile.this,taklamanus.class);
             startActivity(intent);
         }
     });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager manager = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
                if(null!=activeNetwork){
                    if(e1.length()==0){
                        e1.setError("Enter name");
                    }
                    else if(e1.length()<3){
                        e1.setError("Thats too short!");
                    }
                    else if(e2.length()==0){
                        e2.setError("Enter Instagram id");
                    }
                    else{p5.setVisibility(View.VISIBLE);
                        String name = e1.getText().toString();
                        FirebaseDatabase.getInstance().getReference().child("users")
                                .child(FirebaseAuth.getInstance().getUid()).child("name").
                                setValue(name).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    String instaid = e2.getText().toString();
                                    FirebaseDatabase.getInstance().getReference().child("users")
                                            .child(FirebaseAuth.getInstance().getUid()).child("instaid").
                                            setValue(instaid).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                Toast.makeText(profile.this, "Data updated successfully!", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(profile.this,MainActivity2.class));
                                            } else {
                                                Toast.makeText(profile.this, "Operation failed!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(profile.this, "Operation failed!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });





                    }

                }
                else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(profile.this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
   getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.passs){

            changepass();
        }
        else if(id == R.id.del){
           delete();
        }
        return true;
    }

    public void delete(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(profile.this);

        alertDialog.setTitle("Delete Account"); // Sets title for your alertbox

        alertDialog.setMessage("Are you sure you want to Delete Account ?"); // Message to be displayed on alertbox



        /* When positive (yes/ok) is clicked */
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                finish();

                if(vapar != null)
                {
                    vapar.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(profile.this,"Account deleted successfully!",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(profile.this,"Failed to deleted account!",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }

                acc2.child(userId).removeValue();
                startActivity(new Intent(getApplicationContext(),splash.class));







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
public void changepass()
{
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(profile.this);

    alertDialog.setTitle("Reset Password"); // Sets title for your alertbox

    alertDialog.setMessage("Are you sure you want to Change Password ?"); // Message to be displayed on alertbox

    /* When positive (yes/ok) is clicked */
    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog,int which) {
            finish();






            acc2.child(userId).addValueEventListener(new ValueEventListener() {
       @Override
     public void onDataChange(@NonNull DataSnapshot snapshot) {
           users profile2 = snapshot.getValue(users.class);
            String mail = profile2.fix;
            au.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(profile.this,"Check your email to reset your password!",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(profile.this,"Failed to reset account!",Toast.LENGTH_LONG).show();

                    }
                   startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
           });
       }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
   });




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
        Intent intent = new Intent(profile.this,MainActivity2.class);
        startActivity(intent);
    }
}
