package com.tradememes.memeschain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class uploadfromhere extends AppCompatActivity {
    private static int REQUEST_CODE = 100;

    ImageView i1;
CircleImageView b4,b3;
ImageButton i8;
    String imageUrl="";
    String move="Anurag",date;
    Context context;
    DatabaseReference acc3;
    TextView t8,t20;
    private AdView mAdView;
    public int countmeme = 0;
    public int delobj = 0;
    public String bjug = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_uploadfromhere);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        i8 = (ImageButton)findViewById(R.id.imageButton3);
        i1 = (ImageView) findViewById(R.id.imageView9);
        t8 = (TextView) findViewById(R.id.textView8);
        t20 = (TextView) findViewById(R.id.textView15);
        b4 = (CircleImageView) findViewById(R.id.waytodp);
        b3 = (CircleImageView) findViewById(R.id.floatingActionButton3);
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        acc3 = FirebaseDatabase.getInstance().getReference().child("counter"+date).child(FirebaseAuth.getInstance().getUid());
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        Bundle mbundle = getIntent().getExtras();
        if(mbundle!=null){

            Glide.with(this).load(mbundle.getString("Image")).into(i1);
            Glide.with(this).load(mbundle.getString("Image2")).into(b4);
            t8.setText(mbundle.getString("Name"));
            t20.setText(mbundle.getString("insta"));
            imageUrl = mbundle.getString("Image");

            bjug = mbundle.getString("Image2");

        }

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           askpermission();

            }
        });



        i8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(uploadfromhere.this, uploaderprofile.class);
                intent.putExtra("picture",bjug);
                intent.putExtra("name",t8.getText().toString());
                intent.putExtra("instaid",t20.getText().toString());
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(uploadfromhere.this, uploaderprofile.class);
                intent.putExtra("picture", bjug);
                intent.putExtra("name",t8.getText().toString());
                intent.putExtra("instaid",t20.getText().toString());
                startActivity(intent);
            }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(uploadfromhere.this, uploaderprofile.class);
                intent.putExtra("picture", bjug);
                intent.putExtra("name",t8.getText().toString());
                intent.putExtra("instaid",t20.getText().toString());
                startActivity(intent);
            }
        });


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

    }

    private void askpermission(){
        ActivityCompat.requestPermissions(uploadfromhere.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      if(requestCode == REQUEST_CODE)
      {
          if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
              image();
          }else{
              Toast.makeText(uploadfromhere.this, "Please provide required permission", Toast.LENGTH_SHORT).show();
          }
      }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void image()
    { String myCurrentDateTime = DateFormat.getDateTimeInstance()
            .format(Calendar.getInstance().getTime());
        if(countmeme>1)
        {
            Drawable mDrawable = i1.getDrawable();
            Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

            String path = MediaStore.Images.Media.insertImage(getContentResolver(), mBitmap, myCurrentDateTime, null);
            Uri uri = Uri.parse(path);

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/jpeg");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(intent, "Share Image"));
            delobj = countmeme - 1;
            String z = Integer.toString(delobj);
            acc3.child(z).removeValue();
        }else{
            Toast.makeText(uploadfromhere.this, "Please first upload meme to start sharing", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(uploadfromhere.this, uploadmeme.class));
        }



    }
    public void onBackPressed() {
        Intent intent = new Intent(uploadfromhere.this,homepage.class);
        startActivity(intent);
    }
}