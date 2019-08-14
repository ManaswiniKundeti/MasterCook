package com.mastercook.model;

import com.google.gson.annotations.SerializedName;

public class RecipeIngredients {

    @SerializedName("quantity")
    private Double mIngredientQuantity;
    @SerializedName("measure")
    private String mIngredientMeasure;
    @SerializedName("ingredient")
    private String mIngredient;

    public RecipeIngredients(Double mIngredientQuantity, String mIngredientMeasure, String mIngredient) {
        this.mIngredientQuantity = mIngredientQuantity;
        this.mIngredientMeasure = mIngredientMeasure;
        this.mIngredient = mIngredient;
    }

    public RecipeIngredients() {
    }

    public Double getmIngredientQuantity() {
        return mIngredientQuantity;
    }

    public void setmIngredientQuantity(Double mIngredientQuantity) {
        this.mIngredientQuantity = mIngredientQuantity;
    }

    public String getmIngredientMeasure() {
        return mIngredientMeasure;
    }

    public void setmIngredientMeasure(String mIngredientMeasure) {
        this.mIngredientMeasure = mIngredientMeasure;
    }

    public String getmIngredient() {
        return mIngredient;
    }

    public void setmIngredient(String mIngredient) {
        this.mIngredient = mIngredient;
    }
}
