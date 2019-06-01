package com.wiselap.accounts.users;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wiselap.accounts.R;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityAddUsersBinding;
import com.wiselap.accounts.databinding.ActivityUsersBinding;
import com.wiselap.accounts.model.UserModel;

import java.util.ArrayList;

public class AddUsers extends AppCompatActivity {
    ActivityAddUsersBinding activityAddUsersBinding;
    EditText username,profilename;
    Button next;
    TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddUsersBinding=DataBindingUtil.setContentView(this, R.layout.activity_add_users);
        username=activityAddUsersBinding.usernameId;
        profilename=activityAddUsersBinding.profileId;
        next=activityAddUsersBinding.nextId;
        activityAddUsersBinding.include.title.setText("Add Users");
        activityAddUsersBinding.include.addBtn.setVisibility(View.GONE);
        activityAddUsersBinding.include.editBtn.setVisibility(View.GONE);
        activityAddUsersBinding.include.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AddUsers.this,Users.class));
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // data taking from add users
                if(isValid()){
                    //startActivity(new Intent(AddUsers.this,Users.class));
                    Intent intent=new Intent();
                    String user=username.getText().toString();
                    String profile=profilename.getText().toString();
                    intent.putExtra("user",user);
                    intent.putExtra("profile",profile);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else
                    Toast.makeText(AddUsers.this,"Error",Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean isValid() {
        if(!(username.getText().toString().equals("")) && !(profilename.getText().toString().equals("")))
        {
            return true;
        }
        else
            return false;
    }
}
