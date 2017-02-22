package com.awave.issa_recipe.view;

import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.awave.issa_recipe.R;
import com.awave.issa_recipe.api.RecipeApi;
import com.awave.issa_recipe.databinding.ActivityRecipeListBinding;
import com.awave.issa_recipe.model.ApiResponse;
import com.awave.issa_recipe.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeList extends AppCompatActivity {
    private static final String TAG = "RecipeList";
    private RecipeAdapter mAdapter;
    private ActivityRecipeListBinding mBinding;


    private Retrofit mRetrofit;
    private RecipeApi mRecipeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_list);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRecipeApi = mRetrofit.create(RecipeApi.class);

        handleRecipeSearch(getIntent().getExtras().getString(Constants.INGREDIENT_QUERY), 1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.recipeList.setLayoutManager(layoutManager);
        mBinding.recipeList.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_divider));
        mBinding.recipeList.addItemDecoration(dividerItemDecoration);
    }

    private void handleRecipeSearch(String ingredients, int page) {
        mRecipeApi.getRecipeByIngredient(ingredients, page)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {
                            if (!response.body().recipes.isEmpty()) {
                                Log.d(TAG, "onResponse: recipes: " + response.body().recipes.size());
                                mAdapter = new RecipeAdapter(response.body().recipes);
                                mBinding.recipeList.setAdapter(mAdapter);
                            } else {
                                Toast.makeText(RecipeList.this, "No recipies :(", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {

                    }
                });
    }

}
