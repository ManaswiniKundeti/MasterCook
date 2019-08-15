package com.mastercook.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mastercook.R;
import com.mastercook.model.RecipeData;
import com.mastercook.model.RecipeSteps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StepDetailActivity extends AppCompatActivity {

    private static final String TAG = StepDetailActivity.class.getSimpleName();
    public static final String PARAM_STEP_ID = TAG + ".paramStepId";

    private List<RecipeData> mRecipieDataList = new ArrayList<>();
    private List<RecipeSteps> mStepsList = new ArrayList<>();

    TextView shortDescription;
    TextView description;
    //TextView videoUrl;

    VideoView mStepVideoView;

    Button mForwardButton;
    Button mBackwardButton;

    public int selectedStepId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        setTitle("Step Details");

        shortDescription = findViewById(R.id.step_short_description);
        description = findViewById(R.id.step_description);
        //videoUrl = findViewById(R.id.step_video_url);
        mStepVideoView = findViewById(R.id.step_video_view);
        mBackwardButton = findViewById(R.id.backward_button);
        mForwardButton = findViewById(R.id.forward_button);

        mBackwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedStepId = selectedStepId - 1;
                getStepsData(selectedStepId);

            }
        });

        mForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedStepId = selectedStepId + 1;
                getStepsData(selectedStepId);
            }
        });

        getRecipeDetails();

        Intent intentThatStartedThisActivity = getIntent();
        if(intentThatStartedThisActivity.hasExtra(PARAM_STEP_ID)) {
            selectedStepId = intentThatStartedThisActivity.getIntExtra(PARAM_STEP_ID, -1);
            if (selectedStepId == -1) {
                Log.e(TAG, "No recipe id on intent, finishing activity");
                finish();
                return;
            }
            getStepsData(selectedStepId);
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

            Type listType = new TypeToken<List<RecipeData>>() {
            }.getType();

            List<RecipeData> recipeList = new Gson().fromJson(loadJSONFromFile(), listType);

            mRecipieDataList.clear();
            mRecipieDataList.addAll(recipeList);
            mStepsList.clear();
            for(int num = 0; num < recipeList.size(); num++){
                mStepsList.addAll(recipeList.get(num).getmDishCreationSteps());

            }

        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }

    }

    private void getStepsData(int selectedStepId) {
        RecipeSteps stepDetails = null;
        for(RecipeSteps step : mStepsList ){
            if(step.getmStepId() == selectedStepId){
                stepDetails = step;
                break;
            }
        }
        if(stepDetails == null){
            Log.d(TAG, "Step with step id" + selectedStepId + "not found");
            return;
        }
        shortDescription.setText(stepDetails.getmStepShortDescription());
        description.setText(stepDetails.getmStepDescription());

        String videoPath = stepDetails.getmStepVideoURL();
        String thumbnailVideoPath = stepDetails.getmStepThumbnailURL();
        if(isNullOrEmpty(videoPath)){
            if(isNullOrEmpty(thumbnailVideoPath)){
                Toast.makeText(this, "Video is not available", Toast.LENGTH_SHORT).show();
            }else{
                mStepVideoView.setVideoPath(thumbnailVideoPath);
                mStepVideoView.setMediaController(new MediaController(this));
                mStepVideoView.requestFocus();
                mStepVideoView.start();
            }
        } else {
            mStepVideoView.setVideoPath(videoPath);
            mStepVideoView.setMediaController(new MediaController(this));
            mStepVideoView.requestFocus();
            mStepVideoView.start();
        }

    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;
        return true;
    }

//    private void playVideoOnClick(String videoUrlString) {
//        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrlString));
//        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrlString));
//
//        try {
//            if(appIntent != null){
//                startActivity(appIntent);
//            } else {
//                videoUrl.setText("No Video available");
//            }
//
//        } catch (ActivityNotFoundException ex){
//            if(webIntent != null){
//                startActivity(webIntent);
//            } else {
//                videoUrl.setText("No Video available");
//            }
//
//        }
//    }
}
