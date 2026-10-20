package com.tradememes.memeschain;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<productviewholder> {
    private Context pcontext;
    private List<Model> prolist;
    Bitmap bitmap;


    public MyAdapter(Context pcontext, List<Model> prolist) {
        this.pcontext = pcontext;
        this.prolist = prolist;
    }

    @Override
    public productviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.memelayout,parent,false);
        return new productviewholder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull productviewholder holder, int position) {
        Glide.with(pcontext).load(prolist.get(position).getImageUrl()).into(holder.z1);
        Glide.with(pcontext).load(prolist.get(position).getDp()).into(holder.good);
        holder.bad.setText(prolist.get(position).getInsid());
        holder.bad2.setText(prolist.get(position).getInsi());
        holder.mccard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(pcontext,uploadfromhere.class);
                intent.putExtra("Image",prolist.get(holder.getAdapterPosition()).getImageUrl());
                intent.putExtra("Image2",prolist.get(holder.getAdapterPosition()).getDp());
                intent.putExtra("Name",prolist.get(holder.getAdapterPosition()).getInsid());
                intent.putExtra("insta",prolist.get(holder.getAdapterPosition()).getInsi());



                pcontext.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return prolist.size();
    }

    public void filteredlist(ArrayList<Model> filterlist) {
        prolist = filterlist;
        notifyDataSetChanged();
    }
}

class  productviewholder extends RecyclerView.ViewHolder {
    ImageView z1;
Button b1,b2;
CardView mccard;
CircleImageView good;
TextView bad,bad2;


    public productviewholder( View itemView) {
        super(itemView);

        z1 = itemView.findViewById(R.id.ivmage);
        mccard = itemView.findViewById(R.id.mycard);
        good = itemView.findViewById(R.id.abc1);
        bad = itemView.findViewById(R.id.instaid);
        bad2 = itemView.findViewById(R.id.instaid2);
    }
}
