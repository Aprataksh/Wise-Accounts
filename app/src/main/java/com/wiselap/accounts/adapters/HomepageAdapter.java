package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wiselap.accounts.R;

public class HomepageAdapter extends RecyclerView.Adapter<HomepageAdapter.RecyclerViewHolder> {

    Context context;
    String[] items;
    int[] images;
    OnItemViewCLickListener onItemViewCLickListener;

    public HomepageAdapter(Context context, String[] items, int[] images,OnItemViewCLickListener onItemViewCLickListener) {
        this.context = context;
        this.items = items;
        this.images = images;
        this.onItemViewCLickListener=onItemViewCLickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_list, viewGroup, false);
        return new RecyclerViewHolder(view,onItemViewCLickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {

        recyclerViewHolder.imageView.setImageResource(images[i]);
        recyclerViewHolder.textView.setText(items[i]);

        /*recyclerViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return items.length;
    }


    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemViewCLickListener onItemViewCLickListener;
        TextView textView;
        ImageView imageView;
        RecyclerViewHolder(@NonNull View itemView, OnItemViewCLickListener onItemViewCLickListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_id);
            imageView = itemView.findViewById(R.id.imageview_id);
            //CardView cardView = itemView.findViewById(R.id.cardview_id);
            this.onItemViewCLickListener=onItemViewCLickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemViewCLickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemViewCLickListener {
        void onItemClick(int postion);
    }
}
