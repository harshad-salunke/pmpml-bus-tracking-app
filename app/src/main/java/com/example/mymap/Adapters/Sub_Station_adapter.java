package com.example.mymap.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymap.Activitys.MainDestination;
import com.example.mymap.Model.SubStation_model;
import com.example.mymap.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Sub_Station_adapter extends RecyclerView.Adapter<Sub_Station_adapter.ViewHolder> {
    Context context;
    ArrayList<SubStation_model> arrayList;

    public  Sub_Station_adapter(Context context,ArrayList<SubStation_model> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.sub_station_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Sub_Station_adapter.ViewHolder holder, int position) {
        SubStation_model s=arrayList.get(position);
        holder.textView.setText(s.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MainDestination.class);
                intent.putExtra("from",s.getMain_from());
                intent.putExtra("to",s.getName());
                intent.putExtra("pay",s.getRupees());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.sub_name);

        }
    }
}
