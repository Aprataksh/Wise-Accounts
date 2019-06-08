package com.wiselap.accounts.home_screen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.wiselap.accounts.Configuration.ConfigurationActivity;
import com.wiselap.accounts.Report.ReportActivity;
import com.wiselap.accounts.SignIn.GoogleSignInUsersActivity;

import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.HomepageAdapter;
import com.wiselap.accounts.databinding.ActivityHomepageBinding;
import com.wiselap.accounts.expense.Expenses;
import com.wiselap.accounts.model.Configuration;
import com.wiselap.accounts.users.Users;

public class Homepage extends AppCompatActivity implements HomepageAdapter.OnItemViewCLickListener {
    GoogleSignInClient mGoogleSignInClient;
    String[] item = {"Users", "Expenses", "Reports", "Configuration"};
    int[] images =
            {R.drawable.ic_person_black_24dp,
                    R.drawable.expense,
                    R.drawable.report,
                    R.drawable.configuration};
    RecyclerView recyclerView;
    Toolbar toolbar;
    ActivityHomepageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homepage);
        recyclerView = findViewById(R.id.recycler_view);
        toolbar = findViewById(R.id.toolbar_home);
        toolbar.setTitle("Homepage");
        HomepageAdapter recyclerViewAdapter = new HomepageAdapter(this, item, images, this);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        binding.toolbarHome.backBtn.setImageResource(R.drawable.home);
        binding.toolbarHome.addBtn.setVisibility(View.GONE);
        binding.toolbarHome.editBtn.setVisibility(View.GONE);
        binding.toolbarHome.delBtn.setImageResource(R.drawable.exit);
        binding.toolbarHome.title.setText("Home");
    }

    public void onClick(View view) {
        AlertBox();
    }

    @Override
    public void onItemClick(int postion) {
        if (postion == 0) {
            startActivity(new Intent(this, Users.class));
            //finish();
        } else if (postion == 1) {
            startActivity(new Intent(this, Expenses.class));
            //finish();
        } else if (postion == 2) {
            startActivity(new Intent(this, ReportActivity.class));
            //finish();
        } else if (postion == 3) {
            startActivity(new Intent(this, ConfigurationActivity.class));
            //finish();
        }
    }

    private void AlertBox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Google Logout").setMessage("Do you want to Logout?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                mGoogleSignInClient = GoogleSignIn.getClient(Homepage.this, gso);
                mGoogleSignInClient.signOut();
                startActivity(new Intent(Homepage.this, GoogleSignInUsersActivity.class));
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }
}
