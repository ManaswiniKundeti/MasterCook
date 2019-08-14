package com.mastercook.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class RecipeDetailActivity extends AppCompatActivity {

    private TextView mQuantityTextView;
    private TextView mMeasureTextView;
    private TextView mIngredientNameTextView;
    private TextView mRecipeTitleTextView;
    private ImageView mRecipeImageView;

    IngredientListAdapter mIngredientListAdapter;
    StepsListAdapter mStepsListAdapter;

    ListView ingredientListView;
    ListView stepsListView;

    private List<RecipeData> mRecipieDataList = new ArrayList<>();
    private List<RecipeIngredients> mIngredientList = new ArrayList<>();
    private List<RecipeSteps> mStepsList = new ArrayList<>();

    private RecipeData mData ;
    private RecipeIngredients mIngredientData;
    private RecipeSteps mStepsData;

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

            /*try {
                JSONArray jsonArray = new JSONArray(loadJSONFromFile());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject eachRecipeObject = jsonArray.getJSONObject(i);

                    int mDishId = eachRecipeObject.getInt("id");
                    String mDishName = eachRecipeObject.getString("name");
                    JSONArray ingredientsArray = eachRecipeObject.getJSONArray("ingredients");
                    JSONObject ingredientObject = ingredientsArray.getJSONObject(i);
                    Double mIngredientQuantity = ingredientObject.getDouble("quantity");
                    String mIngredientMeasure = ingredientObject.getString("measure");
                    String mIngredientName = ingredientObject.getString("ingredient");
                    JSONArray stepsArray = eachRecipeObject.getJSONArray("steps");
                    JSONObject stepsObject = stepsArray.getJSONObject(i);
                    int mStepId = stepsObject.getInt("id");
                    String mStepShortDescription = stepsObject.getString("shortDescription");
                    String mStepDescription = stepsObject.getString("description");
                    String mStepVideoURL = stepsObject.getString("videoURL");
                    String mStepThumbnailURL = stepsObject.getString("thumbnailURL");
                    int mDishServings = eachRecipeObject.getInt("servings");
                    String mDishImage = eachRecipeObject.getString("image");

                    mData = new RecipeData();
                    mIngredientData = new RecipeIngredients();
                    mStepsData = new RecipeSteps();

                    mData.setmDishId(mDishId);
                    mData.setmDishName(mDishName);
                    mIngredientData.setmIngredientQuantity(mIngredientQuantity);
                    mIngredientData.setmIngredientMeasure(mIngredientMeasure);
                    mIngredientData.setmIngredient(mIngredientName);
                    mStepsData.setmStepId(mStepId);
                    mStepsData.setmStepShortDescription(mStepShortDescription);
                    mStepsData.setmStepDescription(mStepDescription);
                    mStepsData.setmStepVideoURL(mStepVideoURL);
                    mStepsData.setmStepThumbnailURL(mStepThumbnailURL);
                    mData.setmDishServings(mDishServings);
                    mData.setmDishImage(mDishImage);

                    mRecipieDataList.add(mData);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
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
