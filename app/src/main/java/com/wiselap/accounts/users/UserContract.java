package com.wiselap.accounts.users;

import com.wiselap.accounts.interfaces.BaseView;

import java.util.List;

public interface UserContract {
    interface view extends BaseView {
        void setUsers(List<UserReturnModel> list);
    }
    interface  presenter{
        public void getUsers(UserMethodModel userMethodModel);
    }
}
