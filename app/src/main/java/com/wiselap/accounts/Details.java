package com.wiselap.accounts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.shashank.sony.fancytoastlib.FancyToast;

import com.wiselap.accounts.SignIn.GoogleSignInUsersActivity;

public class Details extends AppCompatActivity {
    TextView n,e,id;
    Button logout;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent=new Intent();
        String id1=getIntent().getStringExtra("id");
        String name=getIntent().getStringExtra("n");
        String email=getIntent().getStringExtra("e");

        n=findViewById(R.id.Gname);
        e=findViewById(R.id.Gemail);
        id=findViewById(R.id.ID);

        n.setText(name);
        e.setText(email);
        id.setText(id1);

        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                mGoogleSignInClient = GoogleSignIn.getClient(Details.this, gso);
                mGoogleSignInClient.signOut();
                logout.setVisibility(View.GONE);
                FancyToast.makeText(Details.this,"SignOut.", 20,FancyToast.INFO,false).show();
                startActivity(new Intent(Details.this, GoogleSignInUsersActivity.class));
                finish();
            }
        });
    }
}
