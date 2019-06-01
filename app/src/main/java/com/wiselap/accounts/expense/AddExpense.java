package com.wiselap.accounts.expense;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.wiselap.accounts.DatePickerFragment;
import com.wiselap.accounts.R;
import com.wiselap.accounts.Report.ReportContract;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityAddExpenseBinding;
import com.wiselap.accounts.utils.RealPathUtil;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

public class AddExpense extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final int PICK = 100;
    EditText editText;
    ActivityAddExpenseBinding activityAddExpenseBinding;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddExpenseBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_expense);

        activityAddExpenseBinding.expenseEditToolbar.addBtn.setVisibility(View.GONE);
        activityAddExpenseBinding.expenseEditToolbar.editBtn.setVisibility(View.GONE);
        activityAddExpenseBinding.expenseEditToolbar.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpense.this,Expenses.class));
                finish();
            }
        });
        editText = findViewById(R.id.datepicker);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"Date Picker");
            }
        });

        activityAddExpenseBinding.expenseEditToolbar.title.setText("Define Expense");
        activityAddExpenseBinding.choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        activityAddExpenseBinding.addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showText(AddExpense.this,"Expense Added");
            }
        });
    }

    private void openGallery() {
        Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK && resultCode==RESULT_OK  && data!=null)
        {
            imageUri=data.getData();
            String path=RealPathUtil.getRealPathFromURI_API19(this,imageUri);
            FancyToast.makeText(this,"Path = "+path,10,FancyToast.SUCCESS,false).show();
            activityAddExpenseBinding.uploadImageId.setImageURI(imageUri);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String date= DateFormat.getDateInstance().format(calendar.getTime());
        editText.setText(date);
    }
}
