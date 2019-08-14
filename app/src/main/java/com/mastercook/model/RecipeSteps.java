package com.mastercook.model;

import com.google.gson.annotations.SerializedName;

public class RecipeSteps {

    @SerializedName("id")
    private int mStepId;
    @SerializedName("shortDescription")
    private String mStepShortDescription;
    @SerializedName("description")
    private String mStepDescription;
    @SerializedName("videoURL")
    private String mStepVideoURL;
    @SerializedName("thumbnailURL")
    private String mStepThumbnailURL;

    public RecipeSteps(int mStepId, String mStepShortDescription, String mStepDescription, String mStepVideoURL, String mStepThumbnailURL) {
        this.mStepId = mStepId;
        this.mStepShortDescription = mStepShortDescription;
        this.mStepDescription = mStepDescription;
        this.mStepVideoURL = mStepVideoURL;
        this.mStepThumbnailURL = mStepThumbnailURL;
    }

    public RecipeSteps() {
    }

    public int getmStepId() {
        return mStepId;
    }

    public void setmStepId(int mStepId) {
        this.mStepId = mStepId;
    }

    public String getmStepShortDescription() {
        return mStepShortDescription;
    }

    public void setmStepShortDescription(String mStepShortDescription) {
        this.mStepShortDescription = mStepShortDescription;
    }

    public String getmStepDescription() {
        return mStepDescription;
    }

    public void setmStepDescription(String mStepDescription) {
        this.mStepDescription = mStepDescription;
    }

    public String getmStepVideoURL() {
        return mStepVideoURL;
    }

    public void setmStepVideoURL(String mStepVideoURL) {
        this.mStepVideoURL = mStepVideoURL;
    }

    public String getmStepThumbnailURL() {
        return mStepThumbnailURL;
    }

    public void setmStepThumbnailURL(String mStepThumbnailURL) {
        this.mStepThumbnailURL = mStepThumbnailURL;
    }
}
