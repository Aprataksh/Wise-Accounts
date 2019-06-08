package com.wiselap.accounts.Select_Account;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wiselap.accounts.R;
import com.wiselap.accounts.Select_Entity.SelectEntityActivity;
import com.wiselap.accounts.SignIn.Accounts;
import com.wiselap.accounts.adapters.AccountListAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivitySelectAccountBinding;

import java.util.ArrayList;

import javax.inject.Inject;


public class SelectAccountActivity extends BaseActivity implements SelectAccountContract.View, AccountListAdapter.OnAccountListener {


    @Inject
    SelectAccountPresenter<SelectAccountContract.View> mPresenter;

    ActivitySelectAccountBinding account_binding;
    RecyclerView recyclerView;

     AccountListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account_binding = DataBindingUtil.setContentView(this, R.layout.activity_select_account);
        account_binding.toolbar.title.setText(getString(R.string.Select_Account));
        mPresenter.onAttach(this);
        receiveDataFromCallingActvitiy();
      }

    private void receiveDataFromCallingActvitiy() {
        ArrayList<Accounts>accounts = (ArrayList<Accounts>) getIntent().getSerializableExtra(AppConstants.ACCOUNTS);
        if (accounts!=null){
          createAdapter(accounts);
        }
    }


     public void createAdapter(ArrayList<Accounts>accountList) {
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
                startActivity(new Intent(SelectAccountActivity.this, SelectEntityActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onAccountClick(int position) {
        showMessage("Clicked at " + position);
    }
}

