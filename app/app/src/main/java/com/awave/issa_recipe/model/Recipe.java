package com.awave.issa_recipe.model;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import com.google.gson.annotations.SerializedName;

import okhttp3.OkHttpClient;

/**
 * Created by awave on 2017-02-21.
 */

public class Recipe {

    @SerializedName("title")
    public String title;
    @SerializedName("href")
    public String url;
    @SerializedName("ingridients")
    public String ingredient;
    @SerializedName("thumbnail")
    public String imageUrl;

}


