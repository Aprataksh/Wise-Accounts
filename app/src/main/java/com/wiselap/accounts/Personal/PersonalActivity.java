package com.wiselap.accounts.Personal;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.wiselap.accounts.R;
import com.wiselap.accounts.Select_Entity.SelectEntity;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityPersonalBinding;
import com.wiselap.accounts.home_screen.Homepage;

import javax.inject.Inject;


public class PersonalActivity extends BaseActivity implements PersonalContract.View {

    ActivityPersonalBinding personal_binding;
    String name;
    String contact_no;
    String address;

    @Inject
    PersonalPresenter<PersonalContract.View> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personal_binding = DataBindingUtil.setContentView(this, R.layout.activity_personal);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        personal_binding.toolbar.title.setText(getString(R.string.Profile_Setup));
        mPresenter.onAttach(this);
    }

    private boolean fetch_data(){

        boolean isValid = true;

        name = personal_binding.nameEdit.getText().toString().trim();
        contact_no = personal_binding.contactEdit.getText().toString().trim();
        address = personal_binding.addressEdit.getText().toString().trim();

        if(name.equals("")){
            personal_binding.nameEdit.setError(getString(R.string.Invalid_Name));
            isValid = false;
        }
        if(address.equals("")) {
            personal_binding.addressEdit.setError(getString(R.string.Invalid_Address));
            isValid = false;
        }
        if(contact_no.length() != 10 || !(contact_no.matches("[6-9][0-9]+"))) {
            personal_binding.contactEdit.setError(getString(R.string.Invalid_Contact));
            isValid = false;
        }
        return isValid;
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.backBtn:
                startActivity(new Intent(PersonalActivity.this, SelectEntity.class));
                showMessage(getString(R.string.Entity_not_created));
                finish();
                break;
            case R.id.next:
                nextBtn();
                break;
        }
    }

    private void nextBtn(){
        if(fetch_data()) {
            mPresenter.sendData(name, contact_no, address);
            Intent intentToHome = new Intent(this, Homepage.class);
            startActivity(intentToHome);
            finish();
        }
    }
}
