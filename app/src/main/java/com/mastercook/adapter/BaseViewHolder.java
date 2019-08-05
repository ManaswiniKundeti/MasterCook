package com.mastercook.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercook.R;

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