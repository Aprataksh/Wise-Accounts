package com.wiselap.accounts.Select_Account;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.AccountListAdapter;
import com.wiselap.accounts.model.Account;
import com.wiselap.accounts.Select_Entity.SelectEntity;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivitySelectAccountBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SelectAccount extends BaseActivity implements SelectAccountContract.View, AccountListAdapter.OnAccountListener {


    @Inject
    SelectAccountPresenter<SelectAccountContract.View> mPresenter;

    ActivitySelectAccountBinding account_binding;
    RecyclerView recyclerView;

    List<Account> accountList;
    AccountListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account_binding = DataBindingUtil.setContentView(this, R.layout.activity_select_account);
        account_binding.toolbar.title.setText(getString(R.string.Select_Account));
        mPresenter.onAttach(this);

        setAccountList();

        mPresenter.fetchAccounts();
        mPresenter.setAdapter();
    }

    private void setAccountList(){
        accountList = new ArrayList<>();
        accountList.add(new Account("ABA Dairy", "Delivery Boy"));
        accountList.add(new Account("AZ Milk and Products", "Owner"));
    }

    @Override
    public void createAdapter() {
        recyclerView = account_binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration hDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable hDivider = ContextCompat.getDrawable(this, R.drawable.line_divider);
        hDecoration.setDrawable(hDivider);
        recyclerView.addItemDecoration(hDecoration);
        adapter = new AccountListAdapter(accountList, this, this);
        recyclerView.setAdapter(adapter);
    }

    public void onClick(View view){
        super.onClick(view);
        switch (view.getId()){
            case R.id.backBtn:
                finish();
                break;
            case R.id.addBtn:
                startActivity(new Intent(SelectAccount.this, SelectEntity.class));
                finish();
                break;
        }
    }

    @Override
    public void onAccountClick(int position) {
        showMessage("Clicked at " + position);
    }
}

