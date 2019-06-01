package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiselap.accounts.R;
import com.wiselap.accounts.model.SalaryReport;

import java.util.List;

public class SalaryReportAdapter extends RecyclerView.Adapter<SalaryReportAdapter.SalaryViewHolder>  {

    private List<SalaryReport> salaryReport;
    private Context context;

    public SalaryReportAdapter(List<SalaryReport> salaryReport, Context context) {
        this.salaryReport = salaryReport;
        this.context = context;
    }

    @NonNull
    @Override
    public SalaryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_expense_report, viewGroup, false);
        return new SalaryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaryViewHolder salaryViewHolder, int i) {
        salaryViewHolder.agent_name.setText(salaryReport.get(i).getName());
        salaryViewHolder.amount.setText(String.valueOf(salaryReport.get(i).getAmount()));
    }

    @Override
    public int getItemCount() {
        return salaryReport.size();
    }

    public class SalaryViewHolder extends RecyclerView.ViewHolder {

        TextView agent_name, amount;
        public SalaryViewHolder(@NonNull View itemView) {
            super(itemView);
            agent_name = (TextView)itemView.findViewById(R.id.expense_type);
            amount = (TextView)itemView.findViewById(R.id.expense_amount);
        }
    }
}
