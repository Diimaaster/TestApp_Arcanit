package com.example.testapp_arcanit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelperAdapterRep extends RecyclerView.Adapter< HelperAdapterRep.MyViewClass > {
    ArrayList< String > Name;

    ArrayList< String > Descriptions;
    ArrayList< String > forks_counts;
    Context context;

    public HelperAdapterRep(ArrayList< String > Name, ArrayList< String > Descriptions, ArrayList< String > forks_counts, Context context) {
        this.Name = Name;
        this.Descriptions = Descriptions;
        this.forks_counts = forks_counts;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rep,parent,false);
        return new MyViewClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, int position) {
        holder.Name.setText(Name.get(position));
        holder.Descriptions.setText(Descriptions.get(position));
        holder.forks_counts.setText(forks_counts.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        return Name.size();
    }

    public static class MyViewClass extends RecyclerView.ViewHolder{
        TextView Name;
        TextView Descriptions;
        TextView forks_counts;
        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Descriptions = itemView.findViewById(R.id.description);
            forks_counts = itemView.findViewById(R.id.count);
        }
    }

}