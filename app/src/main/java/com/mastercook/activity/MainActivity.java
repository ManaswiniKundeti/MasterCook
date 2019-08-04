package com.mastercook.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.mastercook.R;
import com.mastercook.adapter.GridAdapter;

public class MainActivity extends AppCompatActivity {

    GridAdapter cardAdapter;

    private ProgressBar cardsLoadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.activity_main_title));

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.app_name));

        RecyclerView mRecyclerView = findViewById(R.id.cards_recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);


        cardsLoadingProgressBar = findViewById(R.id.cards_loading_progress_bar);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void toggleProgressBar(boolean isLoading) {
        cardsLoadingProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
}
