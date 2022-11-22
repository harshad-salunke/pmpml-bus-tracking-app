package com.example.mymap.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymap.Model.Booking_model;
import com.example.mymap.Activitys.PaymentActivity;
import com.example.mymap.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ItemViewHolder> {

Context context;
List<Booking_model> arrayList;

public BookingAdapter(Context context, List<Booking_model> arrayList){
    this.context=context;
    this.arrayList=arrayList;

}
    @NonNull
    @NotNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.booked_item,parent,false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BookingAdapter.ItemViewHolder holder, int position) {
      Booking_model booking_model=arrayList.get(position);
      holder.booking_date.setText(booking_model.getDate());
      holder.booking_time.setText(booking_model.getTime());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(context, PaymentActivity.class);
              intent.putExtra("from",booking_model.getFrom());
              intent.putExtra("to",booking_model.getTo());
              intent.putExtra("pay",booking_model.getTotal());
              intent.putExtra("date",booking_model.getDate());
              intent.putExtra("time",booking_model.getTime());
              intent.putExtra("ticket_no",booking_model.getTicket_no());
              intent.putExtra("check",false);
             context. startActivity(intent);
          }
      });
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    static class  ItemViewHolder extends RecyclerView.ViewHolder {
        TextView booking_date;
        TextView booking_time;
        public ItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            booking_date=itemView.findViewById(R.id.booked_date);
            booking_time=itemView.findViewById(R.id.booking_time);
        }
    }
}