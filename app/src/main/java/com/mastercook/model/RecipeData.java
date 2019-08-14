package com.mastercook.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeData {
    @SerializedName("id")
    private int mDishId;
    @SerializedName("name")
    private String mDishName;
    @SerializedName("ingredients")
    private List<RecipeIngredients> mDishIngredientList;
    @SerializedName("steps")
    private List<RecipeSteps> mDishCreationSteps;
    @SerializedName("servings")
    private int mDishServings;
    @SerializedName("image")
    private String mDishImage;


    public RecipeData(int mDishId, String mDishName, List<RecipeIngredients> mDishIngredientList, List<RecipeSteps> mDishCreationSteps, int mDishServings, String mDishImage) {
        this.mDishId = mDishId;
        this.mDishName = mDishName;
        this.mDishIngredientList = mDishIngredientList;
        this.mDishCreationSteps = mDishCreationSteps;
        this.mDishServings = mDishServings;
        this.mDishImage = mDishImage;
    }

    public RecipeData() {
    }

    public int getmDishId() {
        return mDishId;
    }

    public void setmDishId(int mDishId) {
        this.mDishId = mDishId;
    }

    public String getmDishName() {
        return mDishName;
    }

    public void setmDishName(String mDishName) {
        this.mDishName = mDishName;
    }

    public int getmDishServings() {
        return mDishServings;
    }

    public void setmDishServings(int mDishServings) {
        this.mDishServings = mDishServings;
    }

    public String getmDishImage() {
        return mDishImage;
    }

    public void setmDishImage(String mDishImage) {
        this.mDishImage = mDishImage;
    }

    public List<RecipeIngredients> getmDishIngredientList() {
        return mDishIngredientList;
    }

    public void setmDishIngredientList(List<RecipeIngredients> mDishIngredientList) {
        this.mDishIngredientList = mDishIngredientList;
    }

    public List<RecipeSteps> getmDishCreationSteps() {
        return mDishCreationSteps;
    }

    public void setmDishCreationSteps(List<RecipeSteps> mDishCreationSteps) {
        this.mDishCreationSteps = mDishCreationSteps;
    }
}
