package com.wiselap.accounts.SignIn;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.wiselap.accounts.R;
import com.wiselap.accounts.Select_Account.SelectAccountActivity;
import com.wiselap.accounts.Select_Entity.SelectEntityActivity;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityGoogleSignInBinding;
import com.wiselap.accounts.home_screen.Homepage;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

public class GoogleSignInUsersActivity extends BaseActivity implements LoginContract.view {

    private static final String TAG = "GoogleSignIN";
    private SignInButton signIn;
    private static final int  RC_SIGN_IN = 1;
    private FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    private ActivityGoogleSignInBinding binding;
    HashMap<String,String> map=new HashMap<>();

    @Inject
    LoginPresenter<LoginContract.view> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_google_sign_in);

        presenter.onAttach(this);
        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInGoogle();
            }
        });
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();

    }


     void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
         showLoadingDialog();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                FancyToast.makeText(GoogleSignInUsersActivity.this," Failed.", 20,FancyToast.ERROR,false).show();
                // ...
            }
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //FancyToast.makeText(GoogleSignInUsers.this,"SignIn Successful", 20,FancyToast.SUCCESS,false).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(coordinatorLayout, "Authentication Failed.", Snackbar.LENGTH_LONG).show();
                            FancyToast.makeText(GoogleSignInUsersActivity.this,"Authentication Failed.", 20,FancyToast.ERROR,false).show();
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        //showLoadingDialog();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            startActivity(new Intent(GoogleSignInUsersActivity.this, Homepage.class));
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
             hideLoadingDialog();
            presenter.sendEmailId(new AuthenticationUsingEmail(personEmail));
            finish();

        }
    }


    @Override
    public void intentToAccounts(ArrayList<Accounts> accounts) {
        Intent intentToAccounts = new Intent(this, SelectAccountActivity.class);
        intentToAccounts.putExtra(AppConstants.ACCOUNTS,accounts);
        startActivity(intentToAccounts);
    }

    @Override
    public void intentToSelectEntitiy() {
        startActivity(new Intent(this, SelectEntityActivity.class));
        finish();
    }

    public void onClick(View view)
    {
       FancyToast.makeText(GoogleSignInUsersActivity.this,"SignOut.", 20,FancyToast.ERROR,false).show();
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


}
