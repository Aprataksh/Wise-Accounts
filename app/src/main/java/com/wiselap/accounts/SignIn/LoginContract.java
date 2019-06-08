package com.wiselap.accounts.SignIn;

import com.wiselap.accounts.interfaces.BaseView;

import java.util.ArrayList;

public interface LoginContract {
    interface view extends BaseView{
        void intentToAccounts(ArrayList<Accounts>accounts);
        void intentToSelectEntitiy();
    }
    interface  presenter{
        void sendEmailId(AuthenticationUsingEmail authenticationUsingEmail);

        void getAccounts(ApplicationUserID applicationUserID);

    }
}
