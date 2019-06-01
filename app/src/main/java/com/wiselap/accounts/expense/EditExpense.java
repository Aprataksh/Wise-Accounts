package com.wiselap.accounts.expense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wiselap.accounts.R;
import com.wiselap.accounts.model.ExpenseModel;

public class EditExpense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        String name = getIntent().getStringExtra("Expense Name");
        String type = getIntent().getStringExtra("Expense Type");

        String amt = getIntent().getStringExtra("Expense Amount");


        Toast.makeText(this, name + "\n" + type + "\n" + amt,
                Toast.LENGTH_SHORT).show();
    }
}
