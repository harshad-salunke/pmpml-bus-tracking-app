package com.example.mymap.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymap.Model.SubStation_model;
import com.example.mymap.Activitys.PaymentActivity;
import com.example.mymap.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DestinationsAtapter  extends RecyclerView.Adapter<DestinationsAtapter.ItemViewHolder>  {


    ArrayList<SubStation_model> arrayList;
    Context context;
    public  DestinationsAtapter(ArrayList<SubStation_model> list,Context context){
        this.arrayList=list;
        this.context=context;
    }
    @NonNull
    @NotNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.destination_item, parent, false);
        return new ItemViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DestinationsAtapter.ItemViewHolder holder, int position) {
        SubStation_model destinations=arrayList.get(position);

        holder.rs.setText("₹"+destinations.getRupees());
        holder.place.setText(destinations.getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PaymentActivity.class);
                intent.putExtra("from",destinations.getMain_from());
                intent.putExtra("to",destinations.getName());
                intent.putExtra("pay",destinations.getRupees());
                intent.putExtra("check",true);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    static class  ItemViewHolder extends RecyclerView.ViewHolder {
        TextView place;
        TextView rs;
        Button button;
        public ItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            place=itemView.findViewById(R.id.from_destination);
            rs=itemView.findViewById(R.id.rs);
            button=itemView.findViewById(R.id.book_btn);
        }
    }

}
