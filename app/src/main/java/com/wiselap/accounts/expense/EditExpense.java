package com.wiselap.accounts.expense;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.wiselap.accounts.DatePickerFragment;
import com.wiselap.accounts.R;
import com.wiselap.accounts.databinding.ActivityEditExpenseBinding;
import com.wiselap.accounts.model.ExpenseModel;

import java.text.DateFormat;
import java.util.Calendar;

public class EditExpense extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ActivityEditExpenseBinding binding;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_expense);
        setToolbar();
        intentRec();
    }

    private void intentRec() {
        Intent intent = getIntent();
        String expense = intent.getStringExtra("Expense Name");
        String remarks = intent.getStringExtra("Expense Type");
        String amount = intent.getStringExtra("Expense Amount");
        position = intent.getIntExtra("Position", 0);
        binding.expenseID.setText(expense);
        //binding.dateID.setText("");
        binding.remarkID.setText(remarks);
        binding.amountID.setText(amount);

    }

    private void setToolbar() {
        binding.toolbarUpdateExpense.title.setText("Define Expense");
        binding.toolbarUpdateExpense.editBtn.setVisibility(View.GONE);
        binding.toolbarUpdateExpense.addBtn.setVisibility(View.GONE);
        binding.toolbarUpdateExpense.delBtn.setVisibility(View.GONE);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBtn:
                finish();
                break;
            case R.id.update_expense:
                Intent intent = new Intent();
                //String date=binding.dateID.getText().toString();
                String expense = binding.expenseID.getText().toString();
                String remarks = binding.remarkID.getText().toString();
                String amount = binding.amountID.getText().toString();
                intent.putExtra("expense", expense);
                //intent.putExtra("date",date);
                intent.putExtra("remarks", remarks);
                intent.putExtra("amount", amount);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.date_ID:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
                break;
        }
    }

    /*private void itemChanged(String expense, String remarks, String amount) {
        Toast.makeText(this, "" + position, Toast.LENGTH_LONG).show();
        //arrayList.remove(position);
        //arrayList.set(position, new ExpenseModel("Shivam", "Pune to Delhi", "800"));
        //expenseAdapter.notifyItemChanged(position);

        //super.expenseAdapter.notifyItemRemoved(position);
        //arrayList.add(position,new ExpenseModel(expense,remarks,amount));
        //expenseAdapter.notifyItemChanged(position);
    }*/

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String date = DateFormat.getDateInstance().format(calendar.getTime());
        binding.dateID.setText(date);
    }
}
