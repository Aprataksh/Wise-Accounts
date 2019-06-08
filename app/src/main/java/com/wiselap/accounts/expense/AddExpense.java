package com.wiselap.accounts.expense;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.wiselap.accounts.DatePickerFragment;
import com.wiselap.accounts.R;
import com.wiselap.accounts.databinding.ActivityAddExpenseBinding;
import com.wiselap.accounts.utils.RealPathUtil;

import java.text.DateFormat;
import java.util.Calendar;

public class AddExpense extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final int PICK = 100;
    ActivityAddExpenseBinding activityAddExpenseBinding;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddExpenseBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_expense);
        initToolBar();
    }

    private void initToolBar() {
        activityAddExpenseBinding.expenseEditToolbar.title.setText("Define Expense");
        activityAddExpenseBinding.expenseEditToolbar.addBtn.setVisibility(View.GONE);
        activityAddExpenseBinding.expenseEditToolbar.editBtn.setVisibility(View.GONE);
        activityAddExpenseBinding.expenseEditToolbar.delBtn.setVisibility(View.GONE);
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.backBtn:
                finish();
                break;

            case R.id.add_expense_ID:
                Intent intent = new Intent();
                String date = activityAddExpenseBinding.dateID.getText().toString();
                String expense = activityAddExpenseBinding.expenseID.getText().toString();
                String remarks = activityAddExpenseBinding.remarkID.getText().toString();
                String amount = activityAddExpenseBinding.amountID.getText().toString();
                intent.putExtra("expense", expense);
                intent.putExtra("date", date);
                intent.putExtra("remarks", remarks);
                intent.putExtra("amount", amount);
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.date_ID:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
                break;

            case R.id.upload_btn_ID:
                openGallery();
                break;

        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            String path = RealPathUtil.getRealPathFromURI_API19(this, imageUri);
            FancyToast.makeText(this, "Path = " + path, 10, FancyToast.SUCCESS, false).show();
            activityAddExpenseBinding.uploadImageID.setImageURI(imageUri);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String date = DateFormat.getDateInstance().format(calendar.getTime());
        activityAddExpenseBinding.dateID.setText(date);
    }
}
