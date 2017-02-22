package com.awave.issa_recipe.api;

import com.awave.issa_recipe.model.ApiResponse;
import com.awave.issa_recipe.model.Recipe;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by awave on 2017-02-21.
 */

public interface RecipeApi {
    @GET("api")
    Call<ApiResponse> getRecipeByIngredient(@Query("i") String ingredients, @Query("p") int page);
}
