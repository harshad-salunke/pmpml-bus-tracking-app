package com.example.mymap.Adapters;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymap.Model.Main_station;
import com.example.mymap.R;
import com.example.mymap.Activitys.SubStationActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainStationAdapter  extends RecyclerView.Adapter<MainStationAdapter.ItemViewHolder> implements Filterable {

Context context;
List<Main_station> arrayList;
List<Main_station> all_arrayList;

public  MainStationAdapter(Context context,List<Main_station> arrayList){
    this.context=context;
    this.arrayList=arrayList;
    all_arrayList=new ArrayList<>();
    all_arrayList.addAll(arrayList);
}
    @NonNull
    @NotNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.main_station_item,parent,false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainStationAdapter.ItemViewHolder holder, int position) {
        Main_station station=arrayList.get(position);
        holder.from.setText(station.getFrom());
        holder.to.setText(station.getTo());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SubStationActivity.class);
                intent.putExtra("from",station.getFrom());
                intent.putExtra("to",station.getTo());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public Filter getFilter() {

        return myFilter;
    }

    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Main_station> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(all_arrayList);
            } else {
                for (Main_station main_station: all_arrayList) {
                    if (main_station.getFrom().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(main_station);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            arrayList.clear();
            arrayList.addAll((Collection<? extends Main_station>) filterResults.values);
            notifyDataSetChanged();
        }
    };
    public void filterList(ArrayList<Main_station> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        arrayList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
        Log.d("checedData",arrayList.toString());
    }

public void updateList(String charSequence){

    List<Main_station> filteredList = new ArrayList<>();

    if (charSequence == null || charSequence.length() == 0) {
        filteredList.addAll(all_arrayList);
    } else {
        for (Main_station main_station: all_arrayList) {
            if (main_station.getFrom().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                filteredList.add(main_station);
            }
        }
    }
    arrayList=filteredList;

    notifyDataSetChanged();

}
    static class  ItemViewHolder extends RecyclerView.ViewHolder {
        TextView from;
        TextView to;
        CardView cardView;
        public ItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            from=itemView.findViewById(R.id.from_station);
            to=itemView.findViewById(R.id.booked_date);
            cardView=itemView.findViewById(R.id.MS_cardView);
        }
    }
}