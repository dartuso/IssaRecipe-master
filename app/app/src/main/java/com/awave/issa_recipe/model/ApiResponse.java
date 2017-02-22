package com.awave.issa_recipe.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by awave on 2017-02-21.
 */

public class ApiResponse {
    @SerializedName("title")
    public String title;
    @SerializedName("version")
    public String ver;
    @SerializedName("href")
    public String url;
    @SerializedName("results")
    public ArrayList<Recipe> recipes;
}
