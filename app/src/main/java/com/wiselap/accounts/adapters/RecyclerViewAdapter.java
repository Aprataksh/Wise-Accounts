package com.wiselap.accounts.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.wiselap.accounts.Configuration.ConfigurationActivity;
import com.wiselap.accounts.R;
import com.wiselap.accounts.Report.ReportActivity;
import com.wiselap.accounts.expense.Expenses;
import com.wiselap.accounts.model.Configuration;
import com.wiselap.accounts.users.Users;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    Context context;
    String[] items;
    int[] images;

    public RecyclerViewAdapter(Context context, String[] items, int[] images) {
        this.context = context;
        this.items = items;
        this.images = images;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_list, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {

        recyclerViewHolder.imageView.setImageResource(images[i]);
        recyclerViewHolder.textView.setText(items[i]);
        recyclerViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0)
                    context.startActivity(new Intent(context, Users.class));
                else if (i == 1)
                    context.startActivity(new Intent(context, Expenses.class));
                else if (i == 2)
                    context.startActivity(new Intent(context, ConfigurationActivity.class));
                else if (i == 3)
                    context.startActivity(new Intent(context, ReportActivity.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.length;
    }


    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        TextView textView = itemView.findViewById(R.id.textview_id);
        ImageView imageView = itemView.findViewById(R.id.imageview_id);
        CardView cardView = itemView.findViewById(R.id.cardview_id);

    }
}
