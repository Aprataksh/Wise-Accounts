package com.wiselap.accounts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiselap.accounts.R;
import com.wiselap.accounts.interfaces.user_edit;
import com.wiselap.accounts.model.UserModel;

import java.util.ArrayList;
import java.util.HashMap;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    ArrayList<UserModel> arrayList;
    ActionMode actionMode;
    Context context;
    int k=-1;
    HashMap<Integer,Boolean> map=new HashMap<>();

    public UsersAdapter(ArrayList<UserModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context=context;
        for(int i=0;i<arrayList.size();i++)
            map.put(i,true);

    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list,viewGroup,false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int i) {
        UserModel userModel=arrayList.get(i);
        usersViewHolder.user.setText(userModel.getUsername());
        usersViewHolder.designation.setText(userModel.getDesignation());
        usersViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setBackgroundResource(R.color.colorAccent);
                if((i==k && map.get(i))|| k==-1 )
                {
                    v.setBackgroundResource(R.color.colorAccent);
                    map.put(i,false);
                    k=i;
                }
                else {
                    v.setBackgroundResource(android.R.color.white);
                    map.put(i,true);
                    if(i==k)
                        k=-1;
                }
                ((user_edit)context).fun();
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder{
        TextView user;
        TextView designation;
        CardView cardView;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            user=itemView.findViewById(R.id.user_id);
            designation=itemView.findViewById(R.id.designation_id);
            cardView=itemView.findViewById(R.id.cardview_user);
        }
    }

}
