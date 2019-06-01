package com.wiselap.accounts.ExpenseType.AddExpenseType;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.wiselap.accounts.R;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityAddExpenseTypeBinding;
import com.wiselap.accounts.model.ExpenseType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class AddExpenseTypeActivity extends BaseActivity implements AddExpenseTypeContract.View {

    ActivityAddExpenseTypeBinding binding;

    @Inject AddExpenseTypePresenter<AddExpenseTypeContract.View> mPresenter;

    static String Operation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_expense_type);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setSpinner();
        binding.amountEdit.setText("0");

        Operation = getIntent().getStringExtra(AppConstants.Operation);

        if(Operation.equals(AppConstants.ADD))
            binding.toolbar.title.setText(getString(R.string.Add_Expense_Type));
        else{
            binding.toolbar.title.setText(getString(R.string.Edit_Expense_Type));
            binding.save.setText(getString(R.string.Update));

           // showMessage(String.valueOf(getIntent().getDoubleExtra(AppConstants.Expense_Type_Name, 0)));
            binding.expenseNameEdit.setText(getIntent().getStringExtra(AppConstants.Expense_Type_Name));
            binding.expenseNameEdit.append("");
            binding.frequencyEdit.setSelection(getIntent().getIntExtra(AppConstants.Frequency, AppConstants.Daily));

            binding.amountEdit.setText(BigDecimal.valueOf(getIntent().getDoubleExtra(AppConstants.Default_Amount, 0)).toPlainString());
        }

        mPresenter.onAttach(this);
    }

    private void setSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddExpenseTypeActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.frequency));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.frequencyEdit.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){
            case R.id.backBtn:
                finish();
                break;
            case R.id.save:
                if (validExpenseType()) {
                    Intent intent = new Intent();

                    intent.putExtra(AppConstants.Expense_Type_Name, binding.expenseNameEdit.getText().toString().trim());

                    intent.putExtra(AppConstants.Frequency, (int) binding.frequencyEdit.getSelectedItemId());
                    if (binding.amountEdit.getText().toString().equals(""))
                        intent.putExtra(AppConstants.Default_Amount, 0);
                    else
                        intent.putExtra(AppConstants.Default_Amount, Double.parseDouble(binding.amountEdit.getText().toString()));
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }
    boolean validExpenseType(){
        boolean isValid = true;
        String expense_name = binding.expenseNameEdit.getText().toString().trim();

        List<ExpenseType> expenseTypeList = mPresenter.fetchExpenseTypeList();

        if(expense_name.equals("")) {
            binding.expenseNameEdit.setError(getString(R.string.Expense_Not_Blank));
            isValid = false;
        }
        if(expenseTypeList != null){
            for(ExpenseType row : expenseTypeList){
                if(row.getExpense_name().equalsIgnoreCase(expense_name)){
                    binding.expenseNameEdit.setError(expense_name + getString(R.string.Already_Exists));
                    isValid = false;
                }
            }
        }
        return isValid;
    }
}
