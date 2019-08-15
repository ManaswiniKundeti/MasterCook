package com.mastercook.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mastercook.CustomItemClickListener;
import com.mastercook.R;
import com.mastercook.adapter.RecyclerViewAdapter;
import com.mastercook.model.RecipeData;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerViewAdapter mRecipeAdapter;

    public List<RecipeData> mRecipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.activity_main_title));

        RecyclerView mRecyclerView = findViewById(R.id.cards_recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        getRecipies();

        mRecipeAdapter = new RecyclerViewAdapter(MainActivity.this, mRecipeList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "recipe's clicked position:" + position);
                Context context = MainActivity.this;
                Class destinationActivity = RecipeDetailActivity.class;
                Intent createRecipeDetailActivityIntent = new Intent(context, destinationActivity);

                int selectedRecipeId = mRecipeList.get(position).getmDishId();

                createRecipeDetailActivityIntent.putExtra(RecipeDetailActivity.PARAM_RECIPE_ID, selectedRecipeId);
                startActivity(createRecipeDetailActivityIntent);
            }
        });
        mRecyclerView.setAdapter(mRecipeAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings_icon) {
            Toast.makeText(this, "This is a settings toast", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    public String loadJSONFromFile() {
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
        Log.d(TAG, "Loaded json from assets file in MainActivity");
        return json;
    }

    private void getRecipies() {
        try {
            Type listType = new TypeToken<List<RecipeData>>() {}.getType();
            List<RecipeData> recipeList = new Gson().fromJson(loadJSONFromFile(), listType);

            mRecipeList.clear();
            mRecipeList.addAll(recipeList);
            Log.d(TAG, "recipe data added to recipe list");
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }
}
