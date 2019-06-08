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
import com.wiselap.accounts.model.UserModel;
import com.wiselap.accounts.users.UserReturnModel;

import java.util.ArrayList;
import java.util.HashMap;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    ArrayList<UserReturnModel> arrayList;
    ActionMode actionMode;
    Context context;
    int k=-1;
    HashMap<Integer,Boolean> map=new HashMap<>();

    public UsersAdapter(ArrayList<UserReturnModel> arrayList, Context context) {
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
        UserReturnModel userModel=arrayList.get(i);
        usersViewHolder.user.setText(userModel.getUserName());
        usersViewHolder.designation.setText(userModel.getUserProfile());

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
