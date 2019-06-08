package com.wiselap.accounts.users;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.UsersAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityUsersBinding;
import com.wiselap.accounts.home_screen.Homepage;
import com.wiselap.accounts.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Users extends BaseActivity implements UserContract.view {

    ActivityUsersBinding activityUsersBinding;
    ArrayList<UserModel> arrayList = new ArrayList<>();
    UsersAdapter usersAdapter;

    @Inject
    UserPresenter<UserContract.view> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUsersBinding = DataBindingUtil.setContentView(this, R.layout.activity_users);
        initViews();
        presenter.onAttach(this);
        presenter.getUsers(new UserMethodModel(5));
    }

    private void initViews() {
        //addUsers();
        //usersAdapter = new UsersAdapter(arrayList, this);

        activityUsersBinding.user1Toolbar.title.setText("Users");
    }

    /*private void addUsers() {
        arrayList.add(new UserModel("Ramakant Kale", "Owner"));
        arrayList.add(new UserModel("Pratik Rathi", "Sales Man"));
        arrayList.add(new UserModel("Captain America", "Android"));
        arrayList.add(new UserModel("Tony Stark", "Avengers"));
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String name, profile;
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {

            name = data.getStringExtra("user");
            profile = data.getStringExtra("profile");
            arrayList.add(new UserModel(name, profile));
            usersAdapter.notifyDataSetChanged();

        } else
            FancyToast.makeText(this, "Error Found", 10, FancyToast.CONFUSING, R.drawable.ic_check_black_24dp, false);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                Intent intent=new Intent(Users.this, AddUsers.class);
                intent.putExtra(AppConstants.Operation, AppConstants.ADD);
                startActivityForResult(intent, 1);
                //finish();
                break;
            case R.id.edit_btn:
                Intent intentAdd=new Intent(Users.this, AddUsers.class);
                intentAdd.putExtra(AppConstants.Operation, AppConstants.EDIT);
                startActivityForResult(intentAdd,2);
                break;
            case R.id.backBtn:
                //startActivity(new Intent(Users.this, Homepage.class));
                finish();
                break;
            default:
                FancyToast.makeText(this, "Error on Click", 10, FancyToast.CONFUSING, R.drawable.ic_check_black_24dp, false);
        }

    }

    @Override
    public void setUsers(List<UserReturnModel> list) {
        activityUsersBinding.userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersAdapter = new UsersAdapter((ArrayList<UserReturnModel>) list, this);
        activityUsersBinding.userRecyclerView.setAdapter(usersAdapter);
        usersAdapter.notifyDataSetChanged();
    }
}
