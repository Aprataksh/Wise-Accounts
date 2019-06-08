package com.wiselap.accounts.Report;

import com.wiselap.accounts.interfaces.BaseView;

public interface ReportContract {
    interface View extends BaseView {
        void createAdapter();
    }
    interface Presenter{
        void setAdapter();
    }
}
