package com.mastercook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mastercook.R;

import java.util.List;

public class GridAdapter extends BaseAdapter {


    private final Context context;

    public GridAdapter(Context context) {
       this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.card_item, parent, false);
        }

        TextView movieTextView = convertView.findViewById(R.id.card_info_text);
        movieTextView.setText("I am the first card");
        return convertView;
    }
}
