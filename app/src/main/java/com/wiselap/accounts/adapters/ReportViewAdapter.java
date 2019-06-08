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

import java.util.List;

public class ReportViewAdapter extends RecyclerView.Adapter<ReportViewAdapter.ReportViewHolder>{

    List<Integer> image;
    List<String> type;
    Context context;
    OnReportListener onReportListener;

    public ReportViewAdapter(List<Integer> image, List<String> type, Context context, OnReportListener onReportListener) {
        this.image = image;
        this.type = type;
        this.context = context;
        this.onReportListener = onReportListener;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.report_list_row, viewGroup, false);
        return new ReportViewHolder(v, onReportListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder reportViewHolder, int i) {
        reportViewHolder.imageView.setImageResource(image.get(i));
        reportViewHolder.textView.setText(type.get(i));
    }

    @Override
    public int getItemCount() {
        return image.size();
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textView;
        OnReportListener onReportListener;

        public ReportViewHolder(@NonNull View itemView, OnReportListener onReportListener) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            this.onReportListener = onReportListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onReportListener.onReportClick(getAdapterPosition());
        }
    }
    public interface OnReportListener{
        void onReportClick(int position);
    }
}
