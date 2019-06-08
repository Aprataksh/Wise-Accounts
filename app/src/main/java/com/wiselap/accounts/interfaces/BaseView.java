package com.wiselap.accounts.interfaces;

import android.content.Context;
import android.graphics.drawable.VectorDrawable;
import android.support.v4.content.ContextCompat;

public interface BaseView {
    void showLoadingDialog();
    void hideLoadingDialog();
    void showMessage(String message);
    void showText(Context context,String str);

}
