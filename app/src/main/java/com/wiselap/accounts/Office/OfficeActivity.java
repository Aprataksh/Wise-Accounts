package com.wiselap.accounts.Office;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.wiselap.accounts.R;
import com.wiselap.accounts.Select_Entity.SelectEntityActivity;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityOfficeBinding;
import com.wiselap.accounts.home_screen.Homepage;

import javax.inject.Inject;

public class OfficeActivity extends BaseActivity implements OfficeContract.View {

    ActivityOfficeBinding office_binding;
    String office_name;
    String contact_no;
    String owner;
    String address;


    @Inject OfficePresenter<OfficeContract.View> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        office_binding = DataBindingUtil.setContentView(this, R.layout.activity_office);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        office_binding.toolbar.title.setText(getString(R.string.Profile_Setup));

        mPresenter.onAttach(this);
        office_binding.emailIdEdit.setText(mPresenter.getEmailId());
    }

    private boolean fetch_data(){
        boolean isValid = true;


        office_name = office_binding.nameEdit.getText().toString().trim();
        contact_no = office_binding.contactEdit.getText().toString().trim();
        owner = office_binding.ownerEdit.getText().toString().trim();
        address = office_binding.addressEdit.getText().toString().trim();

        if(office_name.equals("")){
            office_binding.nameEdit.setError(getString(R.string.Invalid_Office_Name));
            isValid = false;
        }
        if(address.equals("")) {
            office_binding.addressEdit.setError(getString(R.string.Invalid_Address));
            isValid = false;
        }
        if(owner.equals("")){
            office_binding.ownerEdit.setError(getString(R.string.Invalid_Owner));
            isValid = false;
        }
        if(contact_no.length() != 10 || !(contact_no.matches("[6-9][0-9]+"))) {
            office_binding.contactEdit.setError(getString(R.string.Invalid_Contact));
            isValid = false;
        }

        return isValid;
    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.backBtn:
                startActivity(new Intent(OfficeActivity.this, SelectEntityActivity.class));
                showMessage(getString(R.string.Entity_not_created));
                finish();
                break;
            case R.id.next:
                if(fetch_data()){
                    mPresenter.sendData(office_name, contact_no, owner, address);
                    Intent intent = new Intent(OfficeActivity.this, Homepage.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
