package com.mastercook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercook.CustomItemClickListener;
import com.mastercook.R;
import com.mastercook.model.RecipeData;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BaseViewHolder> {

    private Context mContext;
    private List<RecipeData> mRecipieList;
    CustomItemClickListener listener;

    public RecyclerViewAdapter(Context mContext, List<RecipeData> mRecipieList, CustomItemClickListener listener) {
        this.mContext = mContext;
        this.mRecipieList = mRecipieList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        final BaseViewHolder mViewHolder =  new BaseViewHolder(mView);

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, mViewHolder.getAdapterPosition());
            }
        });

        return mViewHolder;
    }


    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        //init the card title & image
        //holder.mCardImage.setImageResource(mRecipieList.get(position).getmDishImage());
        holder.mCardTitle.setText(mRecipieList.get(position).getmDishName());
    }

    @Override
    public int getItemCount() {
        return mRecipieList.size();
    }


    public class BaseViewHolder extends RecyclerView.ViewHolder {

        ImageView mCardImage;
        TextView mCardTitle;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);

            //declare and initialize the image & text views on the card
            mCardImage = itemView.findViewById(R.id.card_image);
            mCardTitle = itemView.findViewById(R.id.card_title);
        }
    }



}

