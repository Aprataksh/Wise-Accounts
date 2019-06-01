package com.wiselap.accounts.expense;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.ExpenseAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityExpensesBinding;
import com.wiselap.accounts.home_screen.Homepage;
import com.wiselap.accounts.model.ExpenseModel;

import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Inject;

public class Expenses extends BaseActivity implements ExpensesContract.View {

    ActivityExpensesBinding activityExpensesBinding;
    RecyclerView mrecyclerView;
    ArrayList<ExpenseModel> arrayList = new ArrayList<>();



    private int k = -1;
    ArrayList<ExpenseModel> expenseList = new ArrayList<>();
    ArrayList<View> viewList = new ArrayList<>();

    @Inject ExpensesPresenter<ExpensesContract.View> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityExpensesBinding = DataBindingUtil.setContentView(this, R.layout.activity_expenses);

        mPresenter.onAttach(this);
        activityExpensesBinding.expenseToolbar.title.setText("Expenses");

        arrayList.add(new ExpenseModel("Ticket:", "Rajasthan to Mumbai", "200"));
        arrayList.add(new ExpenseModel("Ticket:", "Bhopal to Hyderabad", "500"));
        arrayList.add(new ExpenseModel("Ticket:", "Pune to Delhi", "800"));

        mPresenter.setAdapter();

    }

    public void onClick(View view){
        super.onClick(view);
        switch (view.getId()){
            case R.id.backBtn:
                startActivity(new Intent(Expenses.this, Homepage.class));
                finish();
                break;
            case R.id.add_btn:
                startActivity(new Intent(Expenses.this,AddExpense.class));
                break;
            case R.id.edit_btn:
                if(expenseList.isEmpty())
                    showMessage("Choose Expense to Edit");
                else{
                    Intent intent = new Intent(Expenses.this, EditExpense.class);

                    intent.putExtra("Expense Amount", expenseList.get(0).getAmount());
                    intent.putExtra("Expense Type", expenseList.get(0).getExpense_type());
                    intent.putExtra("Expense Name", expenseList.get(0).getExpense_name());
                    startActivity(intent);
                }


        }
    }
    @Override
    public void createAdapter() {
        mrecyclerView=activityExpensesBinding.expenseRecyclerview;
        ExpenseAdapter expenseAdapter=new ExpenseAdapter(this,arrayList);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerView.setAdapter(expenseAdapter);
    }

    @Override
    public void expenseEdit(View v, int i) {
        if(k == -1){
            v.setBackgroundResource(R.color.toolbar_select_account);
            viewList.add(v);
            expenseList.add(arrayList.get(i));
            activityExpensesBinding.expenseToolbar.addBtn.setVisibility(View.GONE);
            k = i;
        }
        else if(k == i){
            v.setBackgroundResource(R.color.colorwhite);
            viewList.clear();
            expenseList.clear();
            activityExpensesBinding.expenseToolbar.addBtn.setVisibility(View.VISIBLE);
            k = -1;
        }
        else{
            if(!viewList.isEmpty()){
                viewList.get(0).setBackgroundResource(R.color.colorwhite);
                viewList.clear();
            }
            v.setBackgroundResource(R.color.toolbar_select_account);
            if(!expenseList.isEmpty())
                expenseList.clear();
            expenseList.add(arrayList.get(i));
            viewList.add(v);
            activityExpensesBinding.expenseToolbar.addBtn.setVisibility(View.GONE);
            k = i;
        }
    }
}
