package com.wiselap.accounts.expense;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.ExpenseAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityExpensesBinding;
import com.wiselap.accounts.model.ExpenseModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class Expenses extends BaseActivity implements ExpensesContract.View, SlyCalendarDialog.Callback {

    ActivityExpensesBinding activityExpensesBinding;
    RecyclerView mrecyclerView;
    ExpenseAdapter expenseAdapter;
    ArrayList<ExpenseModel> arrayList = new ArrayList<>();
    int g;

    private int k = -1;
    ArrayList<ExpenseModel> expenseList = new ArrayList<>();
    ArrayList<View> viewList = new ArrayList<>();

    @Inject
    ExpensesPresenter<ExpensesContract.View> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityExpensesBinding = DataBindingUtil.setContentView(this, R.layout.activity_expenses);
        initViews();
        setToolBar();
        mPresenter.onAttach(this);
        mPresenter.getExpense(new ExpenseMethodModel());
    }

    private void setToolBar() {
        activityExpensesBinding.expenseToolbar.title.setText("Expenses");
    }

    private void initViews() {
        //addExpense();
        mrecyclerView = activityExpensesBinding.expenseRecyclerview;
        /*expenseAdapter = new ExpenseAdapter(this, arrayList);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerView.setAdapter(expenseAdapter*/
    }

    /*private void addExpense() {
        arrayList.add(new ExpenseModel("Ticket:", "Rajasthan to Mumbai", "200"));
        arrayList.add(new ExpenseModel("Food:", "Bhopal to Hyderabad", "500"));
        arrayList.add(new ExpenseModel("Shopping:", "Pune to Delhi", "800"));
        arrayList.add(new ExpenseModel("Furniture:", "Rajasthan to Mumbai", "200"));
    }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            String date = data.getStringExtra("date");
            String expense = data.getStringExtra("expense");
            String remarks = data.getStringExtra("remarks");
            String amount = data.getStringExtra("amount");
            arrayList.add(new ExpenseModel(expense, remarks, amount));
            expenseAdapter.notifyDataSetChanged();
        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            //String date = data.getStringExtra("date");
            String expense = data.getStringExtra("expense");
            String remarks = data.getStringExtra("remarks");
            String amount = data.getStringExtra("amount");
            arrayList.set(k, new ExpenseModel(expense, remarks, amount));
            expenseAdapter.notifyItemChanged(k);
            viewList.get(0).setBackgroundResource(R.color.colorwhite);
            viewList.clear();
            expenseList.clear();
            activityExpensesBinding.expenseToolbar.addBtn.setVisibility(View.VISIBLE);
            k = -1;
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.backBtn:
                //startActivity(new Intent(Expenses.this, Homepage.class));
                finish();
                break;
            case R.id.add_btn:
                startActivityForResult(new Intent(Expenses.this, AddExpense.class), 1);
                break;
            case R.id.edit_btn:
                if (expenseList.isEmpty())
                    FancyToast.makeText(this, "Choose Expense to Edit", 20, R.drawable.ic, false).show();
                else {
                    Intent intent = new Intent(Expenses.this, EditExpense.class);
                    intent.putExtra("Expense Amount", expenseList.get(0).getAmount());
                    intent.putExtra("Expense Type", expenseList.get(0).getExpense_type());
                    intent.putExtra("Expense Name", expenseList.get(0).getExpense_name());
                    //intent.putExtra("Position",k);
                    startActivityForResult(intent, 2);
                }
                break;
            case R.id.datepick_ID:
                new SlyCalendarDialog()
                        .setSingle(false)
                        .setFirstMonday(false)
                        .setCallback(this)
                        .setSelectedTextColor(R.color.colorblue)
                        .setSelectedColor(R.color.colorblue)
                        .setHeaderColor(R.color.colorblue)
                        .show(getSupportFragmentManager(), "Expense_Date_Pick");
                break;
            case R.id.del_btn:
                if (k==-1)
                    FancyToast.makeText(this, "Choose Expense to Delete", 20, R.drawable.ic, false).show();
                else
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Expenses.this);
                    alertDialogBuilder.setTitle("Do you really want to delete expense ?").setMessage("").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            delete(k);
                            viewList.get(0).setBackgroundResource(R.color.colorwhite);
                            viewList.clear();
                            expenseList.clear();
                            activityExpensesBinding.expenseToolbar.addBtn.setVisibility(View.VISIBLE);
                            k = -1;
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Nothing
                        }
                    }).show();
                }
        }
    }

    @Override
    public void expenseEdit(View v, int i) {
        g=i;
        if (k == -1) {
            v.setBackgroundResource(R.color.colorSelect);
            viewList.add(v);
            expenseList.add(arrayList.get(i));
            activityExpensesBinding.expenseToolbar.addBtn.setVisibility(View.GONE);
            k = i;
        } else if (k == i) {
            v.setBackgroundResource(R.color.colorwhite);
            viewList.clear();
            expenseList.clear();
            activityExpensesBinding.expenseToolbar.addBtn.setVisibility(View.VISIBLE);
            k = -1;
        } else {
            if (!viewList.isEmpty()) {
                viewList.get(0).setBackgroundResource(R.color.colorwhite);
                viewList.clear();
            }
            v.setBackgroundResource(R.color.colorSelect);
            if (!expenseList.isEmpty())
                expenseList.clear();
            expenseList.add(arrayList.get(i));
            viewList.add(v);
            activityExpensesBinding.expenseToolbar.addBtn.setVisibility(View.GONE);
            k = i;
        }
    }

    @Override
    public void delete(int i) {
        arrayList.remove(i);
        expenseAdapter.notifyDataSetChanged();
    }

    @Override
    public void setExpense(List<ExpenseReturnModel> list) {
        arrayList=(ArrayList)list;
        expenseAdapter = new ExpenseAdapter(this, (ArrayList<ExpenseReturnModel>) list);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerView.setAdapter(expenseAdapter);
        expenseAdapter.notifyDataSetChanged();
    }


    @Override
    public void onCancelled() {
        //Nothing
    }

    @Override
    public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {
        if (firstDate != null) {
            if (secondDate == null) {
                FancyToast.makeText(
                        this,
                        "Select Date Error",
                        Toast.LENGTH_LONG

                ).show();
            } else {
                String expDate=getString(R.string.period, new SimpleDateFormat(getString(R.string.dateFormat), Locale.getDefault()).format(firstDate.getTime()), new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault()).format(secondDate.getTime()));
                //Toast.makeText(this,expDateFrom+"...."+expDateTo,Toast.LENGTH_LONG).show();
                activityExpensesBinding.datefromID.setText(expDate.substring(0,expDate.length()-5));

            }
        }
    }
}
