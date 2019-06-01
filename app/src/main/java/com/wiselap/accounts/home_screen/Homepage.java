package com.wiselap.accounts.home_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.RecyclerViewAdapter;


public class Homepage extends AppCompatActivity {

    String[] item={"Users","Expense","Configuration","Reports"};
    int[] images=
            {R.drawable.ic_person_black_24dp,
            R.drawable.expense,
            R.drawable.configuration,
            R.drawable.report};
    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        recyclerView=findViewById(R.id.recycler_view);
        toolbar=findViewById(R.id.toolbar_home);
        toolbar.setTitle("Homepage");
        RecyclerViewAdapter recyclerViewAdapter= new RecyclerViewAdapter(this,item,images);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
