package com.wiselap.accounts.utils;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class Binding_utils {

//    @BindingAdapter({"bind:title”, “bind:plot”})

    @BindingAdapter({"bind:width", "bind:divisionBy"})
    public static void setWidth(View view, int width, double divisionBy) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) (width / divisionBy);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter({"bind:height", "bind:divisionBy"})
    public static void setHeight(View view, int height, double divisionBy) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (height / divisionBy);
        view.setLayoutParams(layoutParams);
    }



 /*   @BindingAdapter("realValue")
    public static void setRealValue(EditText editText, double value) {
       editText.setText(String.valueOf(value));
    }


    @BindingAdapter(value = "realValueAttrChanged")
    public static void setListener(EditText editText, final InverseBindingListener listener) {
        if (listener != null) {
            editText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    listener.onChange();
                }
            });
        }
    }

    @InverseBindingAdapter(attribute = "realValue")
    public static double getRealValue(EditText editText) {
        return Double.parseDouble(editText.getText().toString());
    }*/


    @BindingAdapter(value = {"bind:selectedValue", "bind:selectedValueAttrChanged"}, requireAll = false)
    public static void bindSpinnerData(Spinner spinner , String newSelectedValue , final InverseBindingListener inverseBindingListener){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                inverseBindingListener.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        if (newSelectedValue != null) {
            int pos = ((ArrayAdapter<String>) spinner.getAdapter()).getPosition(newSelectedValue);
            spinner.setSelection(pos, true);
        }
    }

    @InverseBindingAdapter(attribute = "selectedValue" , event = "selectedValueAttrChanged")
    public static String getSelectedSpinnerValue(Spinner spinner){
       return  spinner.getSelectedItem().toString();
    }


    @BindingAdapter({"bind:font"})
    public static void setFont(View view, String font) {
        TextView textView = (TextView) view;
        Typeface custom_font = Typeface.createFromAsset(view.getContext().getAssets(),  "fonts/"+font+".ttf");
        textView.setTypeface(custom_font);
    }






}
