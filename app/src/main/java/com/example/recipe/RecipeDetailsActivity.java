package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;

import java.util.Map;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        int recipeId = getIntent().getIntExtra("recipeId", -1);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> back());

        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(v -> edit(recipeId));

        Recipe recipe = RecipeManager.getInstance().getRecipeById(recipeId);

        LinearLayout layout = findViewById(R.id.recipeDetailsLayout);

        setText(R.id.recipeNameTextView, recipe.getName());
        setText(R.id.recipeDescriptionTextView, recipe.getDescription());
        setText(R.id.recipeServingSizeTextView, "Serves: " + recipe.getServingSize());
        setText(R.id.recipePrepTimeTextView, "Prep Time: " + recipe.getPrepTime());

        // Adding Ingredients
        TextView ingredientsTitle = new TextView(this);
        ingredientsTitle.setText("Ingredients");
        ingredientsTitle.setTextColor(Color.BLACK);
        ingredientsTitle.setTextSize(18);
        layout.addView(ingredientsTitle);

        for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
            TextView ingredientView = new TextView(this);
            String ingredientText = "- " + entry.getKey().getName() + ": " + entry.getValue() + " " + entry.getKey().getUnit();
            ingredientView.setText(ingredientText);
            ingredientView.setTextColor(Color.DKGRAY);
            layout.addView(ingredientView);
        }

        // Adding Steps
        TextView stepsTitle = new TextView(this);
        stepsTitle.setText("Steps");
        stepsTitle.setTextColor(Color.BLACK);
        stepsTitle.setTextSize(18);
        layout.addView(stepsTitle);

        int stepNumber = 1;
        for (String step : recipe.getSteps()) {
            TextView stepView = new TextView(this);
            String stepText = stepNumber + ". " + step;
            stepView.setText(stepText);
            stepView.setTextColor(Color.DKGRAY);
            layout.addView(stepView);
            stepNumber += 1;
        }
    }

    private void setText(int textViewId, String text) {
        TextView textView = findViewById(textViewId);
        textView.setText(text);
    }

    public void back() {
        Intent intent = new Intent(RecipeDetailsActivity.this, ViewRecipeActivity.class);
        startActivity(intent);
        finish();
    }

    public void edit(int recipeId){
        Intent intent = new Intent(RecipeDetailsActivity.this, EditRecipeActivity.class);
        intent.putExtra("recipeId", recipeId);
        startActivity(intent);
        finish();
    }
}