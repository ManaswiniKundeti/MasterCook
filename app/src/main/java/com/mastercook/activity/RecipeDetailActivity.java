package com.mastercook.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mastercook.R;
import com.mastercook.adapter.IngredientListAdapter;
import com.mastercook.adapter.StepsListAdapter;
import com.mastercook.model.RecipeData;
import com.mastercook.model.RecipeIngredients;
import com.mastercook.model.RecipeSteps;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class RecipeDetailActivity extends AppCompatActivity {

    private TextView mRecipeTitleTextView;
    private ImageView mRecipeImageView;

    IngredientListAdapter mIngredientListAdapter;
    StepsListAdapter mStepsListAdapter;

    ListView ingredientListView;
    ListView stepsListView;

    private List<RecipeData> mRecipieDataList = new ArrayList<>();
    private List<RecipeIngredients> mIngredientList = new ArrayList<>();
    private List<RecipeSteps> mStepsList = new ArrayList<>();

    private static final String TAG = RecipeDetailActivity.class.getSimpleName();

    public static final String PARAM_RECIPE_ID = TAG + ".paramRecipeId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        setTitle("Recipe Details");

        mRecipeTitleTextView = findViewById(R.id.detail_card_title);
        mRecipeImageView = findViewById(R.id.detail_card_image);
        ingredientListView = findViewById(R.id.ingredient_list_view);
        stepsListView = findViewById(R.id.steps_list_view);

        stepsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d(TAG, "clicked position:" + position);


                Context context = RecipeDetailActivity.this;
                Class destinationActivity = StepDetailActivity.class;
                Intent createStepDetailActivityIntent = new Intent(context, destinationActivity);

                int selectedStepId = mStepsList.get(position).getmStepId();

                createStepDetailActivityIntent.putExtra(StepDetailActivity.PARAM_STEP_ID, selectedStepId);
                startActivity(createStepDetailActivityIntent);
            }
        });

        getRecipeDetails();

        mIngredientListAdapter = new IngredientListAdapter(this, mIngredientList);
        ingredientListView.setAdapter(mIngredientListAdapter);

        mStepsListAdapter = new StepsListAdapter(this, mStepsList);
        stepsListView.setAdapter(mStepsListAdapter);

        Intent intentThatStartedThisActivity = getIntent();
        if(intentThatStartedThisActivity.hasExtra(PARAM_RECIPE_ID)) {
            int selectedRecipeId = intentThatStartedThisActivity.getIntExtra(PARAM_RECIPE_ID, -1);
            if (selectedRecipeId == -1) {
                Log.e(TAG, "No recipe id on intent, finishing activity");
                finish();
                return;
            }
            getRecipeData(selectedRecipeId);

        }

    }

    public String loadJSONFromFile(){
        String json = null;
        try {
            InputStream is = getAssets().open("Recipes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void getRecipeDetails() {
        try {
            Type listType = new TypeToken<List<RecipeData>>() {}.getType();

            List<RecipeData> recipeList = new Gson().fromJson(loadJSONFromFile(), listType);

            mRecipieDataList.clear();
            mRecipieDataList.addAll(recipeList);
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    private void getRecipeData(int selectedRecipeId) {
        RecipeData recipeDetails = null;
        for (RecipeData recipe : mRecipieDataList) {
            if (recipe.getmDishId() == selectedRecipeId) {
                recipeDetails = recipe;
                break;
            }
        }
        if (recipeDetails == null) {
            Log.d(TAG, "Recipe with id " + selectedRecipeId + " not found");
            return;
        }
        mRecipeTitleTextView.setText(recipeDetails.getmDishName());
        mIngredientList.clear();
        mStepsList.clear();
        mIngredientList.addAll(recipeDetails.getmDishIngredientList());
        mStepsList.addAll(recipeDetails.getmDishCreationSteps());
        mIngredientListAdapter.notifyDataSetChanged();
        mStepsListAdapter.notifyDataSetChanged();
    }
}
