package com.wiselap.accounts.Home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wiselap.accounts.R;
import com.wiselap.accounts.databinding.ActivityHomeBinding;
import com.wiselap.accounts.home_screen.Homepage;


public class HomeActivity extends AppCompatActivity {


    ActivityHomeBinding home_binding;
    String name;
    String contact_no;
    String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        home_binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        home_binding.toolbar.title.setText("Profile Setup");
        backBtn();
    }

    private void backBtn(){
        home_binding.toolbar.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean fetch_data(){

        boolean isValid = true;

        name = home_binding.nameEdit.getText().toString().trim();
        contact_no = home_binding.contactEdit.getText().toString().trim();
        address = home_binding.addressEdit.getText().toString().trim();

        if(name.equals("")){
            home_binding.nameEdit.setError("Name is invalid");
            isValid = false;
        }
        if(address.equals("")) {
            home_binding.addressEdit.setError("Address is invalid");
            isValid = false;
        }
        if(contact_no.length() != 10 || !(contact_no.matches("[6-9][0-9]+"))) {
            home_binding.contactEdit.setError("Contact Number is invalid");
            isValid = false;
        }
        return isValid;
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.next:
                nextBtn();
                break;
        }
    }
    private void nextBtn(){
       Intent intentToHome = new Intent(this, Homepage.class);
       startActivity(intentToHome);
    }


}
