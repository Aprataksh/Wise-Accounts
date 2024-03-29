package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiselap.accounts.R;
import com.wiselap.accounts.expense.ExpensesContract;
import com.wiselap.accounts.model.ExpenseModel;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder> {
    Context context;
    ArrayList<ExpenseModel> arrayList;

    public ExpenseAdapter(Context context, ArrayList<ExpenseModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expense_item,viewGroup,false);
        return new ExpenseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHolder expenseHolder, int i) {
            ExpenseModel expenseModel=arrayList.get(i);
            expenseHolder.expensename.setText(expenseModel.getExpense_name());
            expenseHolder.expenseamount.setText(expenseModel.getAmount());
            expenseHolder.expensetype.setText(expenseModel.getExpense_type());

            expenseHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ExpensesContract.View)context).expenseEdit(v, i);
                }
            });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ExpenseHolder extends RecyclerView.ViewHolder{
        TextView expensename,expensetype,expenseamount;
        public ExpenseHolder(@NonNull View itemView) {

            super(itemView);
            expensename=itemView.findViewById(R.id.exp_name);
            expensetype=itemView.findViewById(R.id.exp_type);
            expenseamount=itemView.findViewById(R.id.amount);

        }
    }
}
