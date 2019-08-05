package com.mastercook.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.mastercook.R;
import com.mastercook.adapter.RecyclerViewAdapter;
import com.mastercook.model.RecipieData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerViewAdapter cardAdapter;

    private ProgressBar cardsLoadingProgressBar;

    List<RecipieData> mRecipieList;

    RecipieData mRecipieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.activity_main_title));

        RecyclerView mRecyclerView = findViewById(R.id.cards_recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);


        mRecipieList = new ArrayList<>();
        mRecipieData = new RecipieData("1","Rose Chips","4", R.drawable.potato_roses);
        mRecipieList.add(mRecipieData);
        mRecipieData = new RecipieData("2","Brownies","6", R.drawable.brownies);
        mRecipieList.add(mRecipieData);
        mRecipieData = new RecipieData("3","Irani Chai","1", R.drawable.chai);
        mRecipieList.add(mRecipieData);

        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(MainActivity.this, mRecipieList);
        mRecyclerView.setAdapter(mAdapter);

        //cardsLoadingProgressBar = findViewById(R.id.cards_loading_progress_bar);
    }

//    public void toggleProgressBar(boolean isLoading) {
//        cardsLoadingProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
//    }
}
