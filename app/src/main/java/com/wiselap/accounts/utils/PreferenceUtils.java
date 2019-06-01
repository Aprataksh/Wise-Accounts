package com.wiselap.accounts.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;



@Singleton
public class PreferenceUtils {

  private SharedPreferences sharedPreferences;

  @Inject
  public PreferenceUtils(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
   }



}
