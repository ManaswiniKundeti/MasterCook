package com.mastercook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mastercook.R;
import com.mastercook.model.RecipeSteps;

import java.util.List;

public class StepsListAdapter extends BaseAdapter {

    private Context context;
    private List<RecipeSteps> mRecipeSteps;

    public StepsListAdapter(Context context, List<RecipeSteps> mRecipeSteps) {
        this.context = context;
        this.mRecipeSteps = mRecipeSteps;
    }

    @Override
    public int getCount() {
        return mRecipeSteps.size();
    }

    @Override
    public Object getItem(int i) {
        return mRecipeSteps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.step_row_layout, viewGroup, false);
        }
        TextView shortDescription = view.findViewById(R.id.short_description);
        RecipeSteps steps = mRecipeSteps.get(i);
        shortDescription.setText(steps.getmStepShortDescription());
        return view;
    }
}
