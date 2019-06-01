package com.wiselap.accounts.interfaces;


public interface BasePresenterIntrface<V extends BaseView> {
    void onAttach(V mvpView);
    void onDestroy();
}