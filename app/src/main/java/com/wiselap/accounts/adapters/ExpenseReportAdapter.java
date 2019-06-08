package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiselap.accounts.R;
import com.wiselap.accounts.model.ExpenseReport;

import java.util.List;

public class ExpenseReportAdapter extends RecyclerView.Adapter<ExpenseReportAdapter.ExpenseViewHolder> {

    private List<ExpenseReport> expenseList;
    private Context context;
    OnExpenseReportListener onExpenseReportListener;

    public ExpenseReportAdapter(List<ExpenseReport> expenseList, Context context, OnExpenseReportListener onExpenseReportListener) {
        this.expenseList = expenseList;
        this.context = context;
        this.onExpenseReportListener = onExpenseReportListener;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_expense_report, viewGroup, false);
        return new ExpenseViewHolder(v, onExpenseReportListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder expenseViewHolder, int i) {
        expenseViewHolder.type.setText(expenseList.get(i).getExpense_name());
        expenseViewHolder.amount.setText(String.valueOf(expenseList.get(i).getAmount()));
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView type, amount;
        OnExpenseReportListener onExpenseReportListener;
        public ExpenseViewHolder(@NonNull View itemView, OnExpenseReportListener onExpenseReportListener) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.expense_type);
            amount = (TextView) itemView.findViewById(R.id.expense_amount);
            this.onExpenseReportListener = onExpenseReportListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onExpenseReportListener.onExpenseReportClick(getAdapterPosition());
        }
    }

    public interface OnExpenseReportListener{
        void onExpenseReportClick(int position);
    }
}
