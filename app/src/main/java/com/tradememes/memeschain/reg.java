package com.tradememes.memeschain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class reg extends AppCompatActivity {
    EditText txt1,txt2,txt3,txt4;
    Button regs;
    public static final String TAG = " ";
    FirebaseAuth firebaseAuth;
    Task<Void> acc;
    ProgressBar p3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        txt1=findViewById(R.id.editTextTextPersonName);
        txt2=findViewById(R.id.editTextTextEmailAddress2);
        txt3=findViewById(R.id.editTextTextPassword2);
        txt4=findViewById(R.id.editTextTextPersonName4);
        regs=findViewById(R.id.button4);
        firebaseAuth = FirebaseAuth.getInstance();
p3 = findViewById(R.id.progressBar3);

        regs.setOnClickListener(new View.OnClickListener(){


            public void onClick(View view) {

                if (txt1.length()==0){
                    txt1.setError("Enter Name");
                }
                else if (txt2.length()<10){
                    txt2.setError("Enter Proper Email");
                }
                else if (txt1.length()<3){
                    txt2.setError("Thats too short!");
                }
                else if (txt3.length()==0){
                    txt3.setError("Enter Password");
                }
                else if (txt3.length()<5){
                    txt3.setError("Thats too short!");
                }

                else{p3.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(txt2.getText().toString(),txt3.getText().toString())
                            .addOnCompleteListener(reg.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(), login.class));
                                        String name = txt1.getText().toString();
                                        String fix = txt2.getText().toString();
                                        String fix2 = txt4.getText().toString();
                                        String pas = txt3.getText().toString();

                                        users u = new users(name, fix,fix2, pas);
                                        FirebaseDatabase.getInstance().getReference().child("users")
                                                .child(FirebaseAuth.getInstance().getUid()).
                                                setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(reg.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(reg.this,login.class));

                                                } else {

                                                }
                                            }
                                        });

                                        FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid()).child("url")
                                                .setValue("https://firebasestorage.googleapis.com/v0/b/sahara-cdbe5.appspot.com/o/profilepic%2Fblank.png?alt=media&token=27c00ee6-7159-4fc0-8b8b-f3077b5e576f").addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {

                                                } else {

                                                }
                                            }
                                        });
                                    } else {
                                        Toast.makeText(reg.this,"Account opening failed!",Toast.LENGTH_SHORT).show();
                                        Toast.makeText(reg.this,"Try to login account might already exist!",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });

    }


}