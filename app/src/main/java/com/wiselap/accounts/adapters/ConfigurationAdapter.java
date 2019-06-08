package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wiselap.accounts.R;
import com.wiselap.accounts.model.Configuration;

import java.util.List;

public class ConfigurationAdapter extends RecyclerView.Adapter<ConfigurationAdapter.ConfigurationViewHolder> {


    List<Configuration> confList;
    Context context;
    OnConfigurationListener onConfigurationListener;

    public ConfigurationAdapter(List<Configuration> conf_list, Context context, OnConfigurationListener onConfigurationListener) {
        this.confList = conf_list;
        this.context = context;
        this.onConfigurationListener = onConfigurationListener;
    }

    @NonNull
    @Override
    public ConfigurationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.report_list_row, viewGroup, false);
        return new ConfigurationViewHolder(v, onConfigurationListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfigurationViewHolder configurationViewHolder, int i) {
        configurationViewHolder.textView.setText(confList.get(i).getType());
        configurationViewHolder.imageView.setImageResource(confList.get(i).getImages());
    }

    @Override
    public int getItemCount() {
        return confList.size();
    }


    public class ConfigurationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView textView;
        private OnConfigurationListener onConfigurationListener;

        public ConfigurationViewHolder(@NonNull View itemView, OnConfigurationListener onConfigurationListener) {
            super(itemView);
            this.onConfigurationListener = onConfigurationListener;
            this.imageView = itemView.findViewById(R.id.imageView);
            this.textView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onConfigurationListener.onConfigurationClick(getAdapterPosition());
        }
    }
    public interface OnConfigurationListener{
        void onConfigurationClick(int position);
    }
}
