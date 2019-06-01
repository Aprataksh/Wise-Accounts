package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiselap.accounts.R;
import com.wiselap.accounts.model.Account;

import java.util.List;

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountListHolder> {
    List<Account> account_list;
    Context context;
    OnAccountListener onAccountListener;

    public AccountListAdapter(List<Account> account_list, Context context, OnAccountListener onAccountListener) {
        this.account_list = account_list;
        this.context = context;
        this.onAccountListener = onAccountListener;
    }

    @NonNull
    @Override
    public AccountListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.account_list_row, viewGroup, false);
        return new AccountListHolder(v, onAccountListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountListHolder accountListHolder, int i) {
        accountListHolder.account_name.setText(account_list.get(i).account_name);
        accountListHolder.account_type.setText(account_list.get(i).account_type);
    }

    @Override
    public int getItemCount() {
        return account_list.size();
    }

    public class AccountListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView account_name;
        TextView account_type;
        OnAccountListener onAccountListener;

        public AccountListHolder(@NonNull View itemView, OnAccountListener onAccountListener) {
            super(itemView);
            this.account_name = (TextView) itemView.findViewById(R.id.account_name);
            this.account_type = (TextView) itemView.findViewById(R.id.account_type);
            this.onAccountListener = onAccountListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAccountListener.onAccountClick(getAdapterPosition());
        }
    }

    public interface OnAccountListener{
        void onAccountClick(int position);
    }
}
