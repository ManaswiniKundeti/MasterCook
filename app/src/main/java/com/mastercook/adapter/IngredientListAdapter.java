package com.mastercook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mastercook.R;
import com.mastercook.model.RecipeIngredients;

import java.util.List;

public class IngredientListAdapter extends BaseAdapter {

    private Context mContext;
    private List<RecipeIngredients> mRecipeIngredients;

    public IngredientListAdapter(Context mContext, List<RecipeIngredients> ingredients) {
        this.mContext = mContext;
        this.mRecipeIngredients = ingredients;
    }


    @Override
    public int getCount() {
        return mRecipeIngredients.size();
    }

    @Override
    public RecipeIngredients getItem(int position) {
        return mRecipeIngredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.row_layout, parent, false);
        }

        TextView quantity = convertView.findViewById(R.id.ingredient_quantity);
        TextView measure = convertView.findViewById(R.id.ingredient_measure);
        TextView ingredientName = convertView.findViewById(R.id.ingredient_name);

        RecipeIngredients ingredient = mRecipeIngredients.get(position);

        quantity.setText(ingredient.getmIngredientQuantity().toString());
        measure.setText(ingredient.getmIngredientMeasure());
        ingredientName.setText(ingredient.getmIngredient());

        return convertView;
    }
}
