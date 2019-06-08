package com.wiselap.accounts.ExpenseType;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.wiselap.accounts.Configuration.ConfigurationActivity;
import com.wiselap.accounts.ExpenseType.AddExpenseType.AddExpenseTypeActivity;
import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.ExpenseTypeAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityExpenseTypeBinding;
import com.wiselap.accounts.model.ExpenseType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ExpenseTypeActivity extends BaseActivity implements ExpenseTypeContract.View, ExpenseTypeAdapter.OnExpenseListener {

    final int REQUEST_CODE_ADD = 0;
    final int REQUEST_CODE_EDIT = 1;


    @Inject ExpenseTypePresenter<ExpenseTypeContract.View> mPresenter;


    ExpenseTypeAdapter adapter;
    List<ExpenseType> expenseTypeList = new ArrayList<>();
    ActivityExpenseTypeBinding binding;

    private int k = -1;
    private View selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_expense_type);

        binding.toolbar.title.setText(getString(R.string.Expense_Type));


        mPresenter.onAttach(this);
        setDecoration();
        mPresenter.setAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mPresenter != null){
            mPresenter.setAdapter();
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.backBtn:
                startActivity(new Intent(this, ConfigurationActivity.class));
                finish();
                break;
            case R.id.addBtn:
                Intent intent1 = new Intent(this, AddExpenseTypeActivity.class);

                intent1.putExtra(AppConstants.Operation, AppConstants.ADD);

                startActivityForResult(intent1, REQUEST_CODE_ADD);
                break;
            case R.id.editBtn:
                if(k != -1){
                    Intent intent2 = new Intent(this, AddExpenseTypeActivity.class);

                    intent2.putExtra(AppConstants.Operation, AppConstants.EDIT);
                    intent2.putExtra(AppConstants.Expense_Type_Name, expenseTypeList.get(k).getExpense_name());

                    startActivityForResult(intent2, REQUEST_CODE_EDIT);
                }
                else
                    showMessage(getString(R.string.Invalid_Edit));
                break;
            case R.id.deleteBtn:
                if(k != -1){
                    selected.setBackgroundResource(R.color.colorwhite);

                    setAlertDialog();

                    selected = null;
                    k=-1;
                    binding.toolbar.addBtn.setVisibility(View.VISIBLE);
                }
                else
                    showMessage(getString(R.string.Invalid_Edit));

        }
    }


    void setDecoration(){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration hDecoration = new DividerItemDecoration(binding.recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable hDivider = ContextCompat.getDrawable(this, R.drawable.line_divider);
        if (hDivider != null) {
            hDecoration.setDrawable(hDivider);
        }
        binding.recyclerView.addItemDecoration(hDecoration);
    }

    @Override
    public void createAdapter(List<ExpenseType> expenseTypeList) {
        this.expenseTypeList = expenseTypeList;
        adapter = new ExpenseTypeAdapter(expenseTypeList, this, this );
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onExpenseClick(View view, int position) {
        if(k == -1) {
            k = position;
            view.setBackgroundResource(R.color.OnSelected);
            binding.toolbar.addBtn.setVisibility(View.GONE);
            selected = view;
        }
        else if(k == position) {
            k = -1;
            view.setBackgroundResource(R.color.colorwhite);
            selected = null;
            binding.toolbar.addBtn.setVisibility(View.VISIBLE);
        }
        else{
            k=position;
            selected.setBackgroundResource(R.color.colorwhite);
            view.setBackgroundResource(R.color.OnSelected);
            selected=view;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD){
            if(resultCode != Activity.RESULT_OK){
                showMessage(getString(R.string.No_Expense_Type_Added));
            }
            else
                mPresenter.setAdapter();
        }
        else if (requestCode == REQUEST_CODE_EDIT){
            if(resultCode != Activity.RESULT_OK) {
                showMessage(getString(R.string.No_Expense_Type_Edited));
            }
            else
                mPresenter.setAdapter();
            selected.setBackgroundResource(R.color.colorwhite);
            k=-1;
            selected=null;
            binding.toolbar.addBtn.setVisibility(View.VISIBLE);
        }
    }

    void setAlertDialog(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle(getString(R.string.alert));
        int x = k;

        alertDialog.setMessage(getString(R.string.wanna_delete) + " " + expenseTypeList.get(k).getExpense_name() + "?");

        alertDialog.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.deleteExpenseType(expenseTypeList.get(x).getExpense_name());
                expenseTypeList.remove(x);
                adapter.notifyDataSetChanged();
                dialog.cancel();
            }
        });

        alertDialog.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }

}