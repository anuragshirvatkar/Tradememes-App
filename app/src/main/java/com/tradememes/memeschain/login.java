package com.tradememes.memeschain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class login extends AppCompatActivity {
    Button but;
    EditText user, pass;
    FirebaseAuth fire;
    TextView forgot;
    DatabaseReference acc2;
    FirebaseAuth au;
    ProgressBar p2;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        user= (EditText) findViewById(R.id.editTextTextEmailAddress);
        pass=findViewById(R.id.editTextTextPassword);
        but=findViewById(R.id.button);
        forgot=findViewById(R.id.textView6);
       p2 = (ProgressBar) findViewById(R.id.progressBar2);
        fire =FirebaseAuth.getInstance();

        au = FirebaseAuth.getInstance();

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.length()==0){
                    user.setError("Enter Email");
                }
                else{
                    resetpassword();
                }
            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.length()==0){
                    user.setError("Enter Email");
                }
                else if (pass.length()==0){
                    pass.setError("Enter Password");
                }
                else{

                    fire.signInWithEmailAndPassword(user.getText().toString(),pass.getText().toString())
                            .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        fire.getCurrentUser().sendEmailVerification().
                                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(fire.getCurrentUser().isEmailVerified()){

                                                            startActivity(new Intent(getApplicationContext(),homepage.class));
                                                            Toast.makeText(login.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                                                        }else{
                                                            Toast.makeText(login.this, "Click verification link sent to your mail", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }).addOnCanceledListener(new OnCanceledListener() {
                                            @Override
                                            public void onCanceled() {
                                                Toast.makeText(login.this, "Unable to send email", Toast.LENGTH_SHORT).show();
                                            }
                                        });




                                    } else {
                                        Toast.makeText(login.this,"Login Failed",Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                }
            }
        });
    }
    private void resetpassword(){
        au.sendPasswordResetEmail(user.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(login.this,"Check your email to reset your password!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(login.this,"Failed to reset account!",Toast.LENGTH_LONG).show();

                }
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}