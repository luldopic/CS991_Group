package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recipe.model.Recipe;

import java.util.ArrayList;

public class ViewRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> back());

        LinearLayout recipesContainer = findViewById(R.id.recipes_container);


        ArrayList<Recipe> recipes = RecipeManager.getInstance().getRecipes();

        for (Recipe recipe : recipes) {
            // LinearLayout "box" for each recipe
            LinearLayout recipeLayout = new LinearLayout(this);

            recipeLayout.setOnClickListener(v -> goToRecipe(recipe));

            recipeLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 0, 30);
            recipeLayout.setLayoutParams(layoutParams);
            recipeLayout.setPadding(20, 20, 20, 20);
            recipeLayout.setBackgroundColor(Color.LTGRAY);


            TextView nameTextView = new TextView(this);
            nameTextView.setText("Name: " + recipe.getName());
            recipeLayout.addView(nameTextView);

            TextView descriptionTextView = new TextView(this);
            descriptionTextView.setText("Description: " + recipe.getDescription());
            recipeLayout.addView(descriptionTextView);

            TextView servingSizeTextView = new TextView(this);
            servingSizeTextView.setText("Serving Size: " + recipe.getServingSize());
            recipeLayout.addView(servingSizeTextView);

            TextView prepTimeTextView = new TextView(this);
            prepTimeTextView.setText("Prep Time: " + recipe.getPrepTime());
            recipeLayout.addView(prepTimeTextView);

            recipesContainer.addView(recipeLayout);
        }
    }

    public void back() {
        finish();
    }

    public void goToRecipe(Recipe recipe){
        Intent detailIntent = new Intent(ViewRecipeActivity.this, RecipeDetailsActivity.class);
        detailIntent.putExtra("recipeId", recipe.getId());
        startActivity(detailIntent);
        finish();
    }
}
