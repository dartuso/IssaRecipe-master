package com.awave.issa_recipe.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.awave.issa_recipe.R;
import com.awave.issa_recipe.api.RecipeApi;
import com.awave.issa_recipe.databinding.RecipeCardBinding;
import com.awave.issa_recipe.model.Recipe;
import com.awave.issa_recipe.viewmodel.RecipeViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by awave on 2017-02-21.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeVH> {
    private static final String TAG = "RecipeAdapter";
    private ArrayList<Recipe> mRecipes = new ArrayList<>();

    public RecipeAdapter(ArrayList<Recipe> recipes) {
        this.mRecipes = recipes;
    }
    @Override
    public RecipeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        RecipeCardBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recipe_card,
                parent,
                false
        );
        return new RecipeVH(binding);
    }

    @Override
    public void onBindViewHolder(RecipeVH holder, int pos) {
        holder.binding.recipeTitle.setText(mRecipes.get(pos).title);
        if (!mRecipes.get(pos).imageUrl.isEmpty())
            Picasso.with(holder.binding.getRoot().getContext())
                    .load(mRecipes.get(pos).imageUrl)
                    .into(holder.binding.recipeImg);

        Log.d(TAG, "onBindViewHolder: url: " + mRecipes.get(pos).url);

        holder.binding.getRoot().setOnClickListener(v ->
                v.getContext().startActivity(
                new Intent(Intent.ACTION_VIEW, Uri.parse(mRecipes.get(pos).url))
        ));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public static class RecipeVH extends RecyclerView.ViewHolder {
        public RecipeCardBinding binding;

        public RecipeVH(RecipeCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

//        void bindViewModel(Recipe recipe) {
//            if (binding.getRecipe() == null)
//                binding.setRecipe(new RecipeViewModel());
//        }

//        void bindRepository(Recipe recipe) {
//            if (binding.getViewModel() == null) {
//                binding.setViewModel(new ItemRepoViewModel(itemView.getContext(), repository));
//            } else {
//                binding.getViewModel().setRepository(repository);
//            }
//        }
    }
}
