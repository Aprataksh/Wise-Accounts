package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiselap.accounts.R;
import com.wiselap.accounts.Select_Entity.SelectEntityContract;
import com.wiselap.accounts.model.Item;

import java.util.List;

public class SelectEntityAdapter extends RecyclerView.Adapter<SelectEntityAdapter.ListHolder> {

    private List<Item> itemList;
    private Context context;
    private OnEntityListener onEntityListener;

    public SelectEntityAdapter(List<Item> itemList, Context context, OnEntityListener onEntityListener) {
        this.itemList = itemList;
        this.context = context;
        this.onEntityListener = onEntityListener;
    }

    @NonNull
    @Override

    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.list_row, viewGroup, false);
        return new ListHolder(v, onEntityListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {
        listHolder.item_name.setText(itemList.get(i).getItem_name());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView item_name;
        OnEntityListener onEntityListener;

        public ListHolder(@NonNull View itemView, OnEntityListener onEntityListener) {
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.text1);
            this.onEntityListener = onEntityListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onEntityListener.onEntityClick(getAdapterPosition());
        }
    }
    public interface OnEntityListener{
        void onEntityClick(int position);
    }
}
