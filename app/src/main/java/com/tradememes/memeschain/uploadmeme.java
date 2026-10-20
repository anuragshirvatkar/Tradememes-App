package com.tradememes.memeschain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import de.hdodenhof.circleimageview.CircleImageView;

public class uploadmeme extends AppCompatActivity {
public Button button;
public CircleImageView imageView;
public DatabaseReference databaseReference;
public StorageReference storageReference;
public Uri imageuri;
DatabaseReference acc,acc2,acc3;
String date,str,str2,str3,str4,str5;
ProgressBar p4;

public int countmeme = 0;
    public int countmeme2 = 0;
    public int countmeme3 = 0;
    public int countmeme4 = 0;
    public int countmeme5 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadmeme);




        imageView = (CircleImageView) findViewById(R.id.imageView3);
        button = (Button) findViewById(R.id.button6);
        acc2 = FirebaseDatabase.getInstance().getReference().child("users");
        acc = FirebaseDatabase.getInstance().getReference().child("propic" + FirebaseAuth.getInstance().getUid());
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        acc3 = FirebaseDatabase.getInstance().getReference().child("counter"+date).child(FirebaseAuth.getInstance().getUid());
        databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
        storageReference = FirebaseStorage.getInstance().getReference().child(date);
        p4 = findViewById(R.id.progressBar4);
        acc3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())   {
                    countmeme = (int) snapshot.getChildrenCount();
                }else{

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
str = Integer.toString(countmeme);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager manager = (ConnectivityManager)
                        getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
                if(null!=activeNetwork){

                    if(imageuri != null){
                        uploadtofirebase(imageuri);
                        p4.setVisibility(View.VISIBLE);
                    }
                    else{
                        Toast.makeText(uploadmeme.this, "Please select image", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(uploadmeme.this);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null)
        {
            imageuri = data.getData();
            imageView.setImageURI(imageuri);
        }
    }
    private void uploadtofirebase(Uri uri){
        StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                   @Override
                   public void onSuccess(Uri uri) {

                       acc2.child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               users profile = snapshot.getValue(users.class);
                               if(profile != null)
                               {
                                   String n1 = profile.name;
                                   String final2 = profile.instaid;
                                   acc.child("url").addValueEventListener(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                                           String final1 = snapshot.getValue().toString();

                                           Model model = new Model(uri.toString(),final1,n1,final2);
                                           String modelID = databaseReference.push().getKey();
                                           databaseReference.child(modelID).setValue(model);

                                           acc3.child(str).
                                                   setValue(countmeme).addOnCompleteListener(new OnCompleteListener<Void>() {
                                               @Override
                                               public void onComplete(@NonNull Task<Void> task) {
                                                   one();
                                               }
                                           });

                                           Toast.makeText(uploadmeme.this, "Uploaded successfully!", Toast.LENGTH_SHORT).show();
                                           Intent intent = new Intent(uploadmeme.this, homepage.class);
                                           startActivity(intent);
                                       }

                                       @Override
                                       public void onCancelled(@NonNull DatabaseError error) {

                                       }
                                   });
                               } }
                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {
                               Toast.makeText(uploadmeme.this,"Something went wrong!",Toast.LENGTH_LONG).show();
                           }
                       });

                      String date2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                   }
               });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(uploadmeme.this, "Uploading Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){
        ContentResolver cr =getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
    public void one()
        {
            acc3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists())   {
                        countmeme = (int) snapshot.getChildrenCount();
                    }else{ }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
            str = Integer.toString(countmeme);


            acc3.child(str).
                    setValue(countmeme).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    two();
                }
            });


    }

    public void two()
    {
        acc3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())   {
                    countmeme = (int) snapshot.getChildrenCount();
                }else{ }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        str = Integer.toString(countmeme);


        acc3.child(str).
                setValue(countmeme).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                three();
            }
        });

    }

    public void three()
    {
        acc3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())   {
                    countmeme = (int) snapshot.getChildrenCount();
                }else{ }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        str = Integer.toString(countmeme);


        acc3.child(str).
                setValue(countmeme).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                four();
            }
        });

    }

    public void four()
    {
        acc3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())   {
                    countmeme = (int) snapshot.getChildrenCount();
                }else{ }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        str = Integer.toString(countmeme);


        acc3.child(str).
                setValue(countmeme).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }


    }