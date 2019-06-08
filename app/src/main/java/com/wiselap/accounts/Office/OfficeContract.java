package com.wiselap.accounts.Office;

import com.wiselap.accounts.interfaces.BaseView;

public interface OfficeContract {
    interface View extends BaseView {

    }
    interface Presenter{
        void sendData(String office, String contact, String owner, String address);

        String getEmailId();
    }
}
