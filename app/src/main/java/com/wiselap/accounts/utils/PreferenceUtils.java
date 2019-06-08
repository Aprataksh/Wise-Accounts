package com.wiselap.accounts.utils;

import android.content.SharedPreferences;

import com.wiselap.accounts.constants.AppConstants;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.wiselap.accounts.SignIn.EmailData;


@Singleton
public class PreferenceUtils {

    private SharedPreferences sharedPreferences;

    @Inject
    public PreferenceUtils(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public void saveLoginInfo(EmailData data, String email) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putLong(AppConstants.applicationUserId,data.getApplicationUserId());
        editor.putLong(AppConstants.loginId,data.getLoginId());
        editor.putString(AppConstants.uniqueIdentityField,email);
        editor.commit();
    }

    public void saveAccountingProfileId(long accountingProfileId){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(AppConstants.accountingProfileId, accountingProfileId);
        editor.commit();
    }
    public String getEmailId(){
        return sharedPreferences.getString(AppConstants.uniqueIdentityField,"");
    }



    public long getApplicationUSerId( ){
        return sharedPreferences.getLong(AppConstants.applicationUserId, 0);
    }

    public long getLoginId( ){
        return sharedPreferences.getLong(AppConstants.loginId, 0);
    }


    public void storeAccountingProfileID(long profileId){
        sharedPreferences.edit().putLong(AppConstants.profileId,profileId);
    }

    public long getAccountingProfile(){
        return sharedPreferences.getLong(AppConstants.profileId,0);
    }
    /*
   public void saveLoginData(long applicationjUserId){
      sharedPreferences.edit().putLong(AppConstants.applicationUserId,applicationjUserId).commit();
   }

   public long getApplicationUserId(){
      return sharedPreferences.getLong(AppConstants.applicationUserId,0);
   }*/



}
