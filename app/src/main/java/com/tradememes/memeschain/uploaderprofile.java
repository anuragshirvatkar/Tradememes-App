package com.tradememes.memeschain;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class uploaderprofile extends AppCompatActivity {
CircleImageView i1;
TextView t8,t9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaderprofile);
i1 = (CircleImageView) findViewById(R.id.imageView2222);
t8 = (TextView) findViewById(R.id.textView13) ;
        t9 = (TextView) findViewById(R.id.textView14) ;
        Bundle mbundle = getIntent().getExtras();
        Bitmap bitmap = (Bitmap) this.getIntent().getParcelableExtra("url");
        i1.setImageBitmap(bitmap);
        if(mbundle!=null){

            Glide.with(this).load(mbundle.getString("picture")).into(i1);
            t8.setText(mbundle.getString("name"));
            t9.setText(mbundle.getString("instaid"));

        }
    }
}