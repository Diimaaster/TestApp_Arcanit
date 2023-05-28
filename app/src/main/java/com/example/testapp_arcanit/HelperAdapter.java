package com.example.testapp_arcanit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter< HelperAdapter.MyViewClass > {
    ArrayList< String > Login;
    ArrayList< String > score;

    ArrayList< String > img;
    Context context;

    public HelperAdapter(ArrayList< String > Login, ArrayList< String > img,ArrayList< String > score, Context context) {
        this.Login = Login;
        this.img = img;
        this.score = score;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new MyViewClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, int position) {
        holder.Login.setText(Login.get(position));
        holder.score.setText(score.get(position));
        Picasso.get().load(img.get(position)).into(holder.img);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        return Login.size();
    }

    public static class MyViewClass extends RecyclerView.ViewHolder{
        TextView Login;
        TextView score;
        ImageView img;
        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            Login = itemView.findViewById(R.id.login);
            img = itemView.findViewById(R.id.img);
            score = itemView.findViewById(R.id.score);
        }
    }

}