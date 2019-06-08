package com.wiselap.accounts.Personal;

import com.wiselap.accounts.interfaces.BaseView;

public interface PersonalContract {
    interface View extends BaseView {

    }
    interface Presenter{
        void sendData(String name, String contact, String address);
        String getEmailId();
    }

}
