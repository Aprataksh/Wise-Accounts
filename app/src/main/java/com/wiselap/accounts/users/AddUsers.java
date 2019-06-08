package com.wiselap.accounts.users;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.wiselap.accounts.R;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityAddUsersBinding;
import com.wiselap.accounts.utils.RealPathUtil;

public class AddUsers extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivityAddUsersBinding activityAddUsersBinding;
    EditText username;
    Spinner profilename;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddUsersBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_users);
        username = activityAddUsersBinding.usernameId;
        profilename = activityAddUsersBinding.profileId;
        next = activityAddUsersBinding.nextId;
        activityAddUsersBinding.include.addBtn.setVisibility(View.GONE);
        activityAddUsersBinding.include.editBtn.setVisibility(View.GONE);
        activityAddUsersBinding.include.delBtn.setVisibility(View.GONE);
        if(getIntent().getStringExtra(AppConstants.Operation).equals(AppConstants.EDIT))
        {
            activityAddUsersBinding.include.title.setText("Edit Users");
        }
        else if(getIntent().getStringExtra(AppConstants.Operation).equals(AppConstants.ADD))
        {
            activityAddUsersBinding.include.title.setText("Add Users");
            ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.profile,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            profilename.setAdapter(adapter);
            profilename.setOnItemSelectedListener(this);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // data taking from add users
                    if (isValid()) {
                        //startActivity(new Intent(AddUsers.this,Users.class));
                        Intent intent = new Intent();
                        String user = username.getText().toString();
                        String profile = profilename.getSelectedItem().toString();
                        intent.putExtra("user", user);
                        intent.putExtra("profile", profile);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else
                        Toast.makeText(AddUsers.this, "Error", Toast.LENGTH_LONG).show();
                }
            });
        }

        activityAddUsersBinding.include.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AddUsers.this,Users.class));
                finish();
            }
        });
    }

    private boolean isValid() {
        String checkUserName=username.getText().toString();
        if (!checkUserName.equals("")) {
            return true;
        } else
            return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String profile = parent.getItemAtPosition(position).toString();
        //Toast.makeText(this,""+profile,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this,"Error Select Profile",Toast.LENGTH_LONG).show();
    }
}
