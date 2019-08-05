package com.mastercook.model;

import com.google.gson.annotations.SerializedName;

public class RecipieData {
    @SerializedName("id")
    private String mDishId;
    @SerializedName("name")
    private String mDishName;
    @SerializedName("servings")
    private String mDishServings;
    @SerializedName("image")
    private int mDishImage;


    public RecipieData(String mDishId, String mDishName, String mDishServings, int mDishImage) {
        this.mDishId = mDishId;
        this.mDishName = mDishName;
        this.mDishServings = mDishServings;
        this.mDishImage = mDishImage;
    }

    public String getmDishId() {
        return mDishId;
    }

    public void setmDishId(String mDishId) {
        this.mDishId = mDishId;
    }

    public String getmDishName() {
        return mDishName;
    }

    public void setmDishName(String mDishName) {
        this.mDishName = mDishName;
    }

    public String getmDishServings() {
        return mDishServings;
    }

    public void setmDishServings(String mDishServings) {
        this.mDishServings = mDishServings;
    }

    public int getmDishImage() {
        return mDishImage;
    }

    public void setmDishImage(int mDishImage) {
        this.mDishImage = mDishImage;
    }
}
