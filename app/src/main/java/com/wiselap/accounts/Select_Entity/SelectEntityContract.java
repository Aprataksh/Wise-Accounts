package com.wiselap.accounts.Select_Entity;

import com.wiselap.accounts.interfaces.BaseView;
import com.wiselap.accounts.model.Item;

import java.util.List;

public interface SelectEntityContract {
    interface SEView extends BaseView {
        void showMessage(String s);
        void createAdapter(List<Item> itemList);
    }
    interface SEPresnter{
        void setAdapter();
    }
}
