package com.mastercook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercook.R;
import com.mastercook.model.RecipieData;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<RecipieData> mRecipieList;

    public RecyclerViewAdapter(Context mContext, List<RecipieData> mRecipieList) {
        this.mContext = mContext;
        this.mRecipieList = mRecipieList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new BaseViewHolder(mView);
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        //init the card title & image
        holder.mCardImage.setImageResource(mRecipieList.get(position).getmDishImage());
        holder.mCardTitle.setText(mRecipieList.get(position).getmDishName());
    }

    @Override
    public int getItemCount() {
        return mRecipieList.size();
    }
}