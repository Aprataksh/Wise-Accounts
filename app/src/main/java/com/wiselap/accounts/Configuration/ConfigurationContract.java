package com.wiselap.accounts.Configuration;

import com.wiselap.accounts.interfaces.BaseView;

public interface ConfigurationContract {
    interface View extends BaseView {
        void createAdapter();
    }
    interface Presenter{
        void setAdapter();
    }
}
