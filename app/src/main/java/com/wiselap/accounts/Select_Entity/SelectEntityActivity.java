package com.wiselap.accounts.Select_Entity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wiselap.accounts.Office.OfficeActivity;
import com.wiselap.accounts.Personal.PersonalActivity;
import com.wiselap.accounts.R;
 import com.wiselap.accounts.Select_Account.SelectAccountActivity;
import com.wiselap.accounts.adapters.SelectEntityAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivitySelectEntityBinding;
import com.wiselap.accounts.model.Item;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SelectEntityActivity extends BaseActivity implements SelectEntityContract.SEView, SelectEntityAdapter.OnEntityListener{

    final int ENTITY_OFFICE = 0;
    final int ENTITY_PERSONAL = 1;

    @Inject
    SelectEntityPresenter<SelectEntityContract.SEView> mPresenter;

    SelectEntityAdapter adapter;
    List<Item> item_list = new ArrayList<>();
    RecyclerView recyclerView;
    ActivitySelectEntityBinding select_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        select_binding = DataBindingUtil.setContentView(this, R.layout.activity_select_entity);
        select_binding.toolbar.title.setText(getString(R.string.Select_Entity));
        mPresenter.onAttach(this);

        //Sets the Item List for Select Entity
        //setItem_list();

        //Sets the Recycler View
        setRecyclerView();

        //Sets the adapter for the Recycler View
        mPresenter.setAdapter();
    }

    private void setItem_list(){
        item_list = new ArrayList<>();
        item_list.add(new Item(getString(R.string.Office), 0));
        item_list.add(new Item(getString(R.string.Personal), 1));
    }
    public void onClick(View view){
        super.onClick(view);
        switch (view.getId()){
            case R.id.backBtn:
                startActivity(new Intent(SelectEntityActivity.this, SelectAccountActivity.class));
                showMessage(getString(R.string.Account_not_created));
                finish();
                break;
        }
    }

    private void manageOffice(){
        startActivity(new Intent(this, OfficeActivity.class));
        finish();
    }

    private void managePersonal() {
        startActivity(new Intent(this, PersonalActivity.class));
        finish();
    }
    private void setRecyclerView(){
        recyclerView = select_binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*DividerItemDecoration hDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable hDivider = ContextCompat.getDrawable(this, R.drawable.line_divider);
        hDecoration.setDrawable(hDivider);
        recyclerView.addItemDecoration(hDecoration);*/
    }

    @Override
    public void showMessage(String s) {
        super.showMessage(s);
    }

    @Override
    public void createAdapter(List<Item> itemList) {
        this.item_list = itemList;
        adapter = new SelectEntityAdapter(item_list,this, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onEntityClick(int position) {
        switch (position){
            case ENTITY_OFFICE:
                manageOffice();
                break;
            case ENTITY_PERSONAL:
                managePersonal();
                break;
        }
    }
}
