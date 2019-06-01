package com.wiselap.accounts.interfaces;

public interface BaseePresenter<V extends BaseView> {
    void onAttach(V mvpView);
    void onDestroy();
}