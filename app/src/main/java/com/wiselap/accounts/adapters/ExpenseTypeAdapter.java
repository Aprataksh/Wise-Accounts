package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiselap.accounts.R;
import com.wiselap.accounts.model.ExpenseType;

import java.util.List;

public class ExpenseTypeAdapter extends RecyclerView.Adapter<ExpenseTypeAdapter.ExpenseTypeViewHolder>  {

    private List<ExpenseType> expenseTypeList;
    Context context;
    private OnExpenseListener onExpenseListener;

    public ExpenseTypeAdapter(List<ExpenseType> expenseTypeList, Context context, OnExpenseListener onExpenseListener) {
        this.expenseTypeList = expenseTypeList;
        this.context = context;
        this.onExpenseListener = onExpenseListener;
    }

    @NonNull
    @Override
    public ExpenseTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_row, viewGroup, false);
        return new ExpenseTypeViewHolder(v, onExpenseListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseTypeViewHolder expenseTypeViewHolder, int i) {
        expenseTypeViewHolder.textView.setText(expenseTypeList.get(i).getExpense_name());
    }

    @Override
    public int getItemCount() {
        return expenseTypeList.size();
    }

    public class ExpenseTypeViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView textView;
        OnExpenseListener onExpenseListener;


        public ExpenseTypeViewHolder(@NonNull View itemView, OnExpenseListener onExpenseListener) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.text1);
            this.onExpenseListener = onExpenseListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onExpenseListener.onExpenseClick(v, getAdapterPosition());
        }
    }

    public interface OnExpenseListener{
        void onExpenseClick(View view, int position);
    }

}
