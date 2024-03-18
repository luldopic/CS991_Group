package com.example.recipe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditRecipeActivity extends AppCompatActivity {

    private LinearLayout nameAndDescriptionLayout;
    private LinearLayout ingredientsLayout;
    private LinearLayout stepsLayout;
    private LinearLayout ingredientsButtonLayout;
    private LinearLayout stepsButtonLayout;
    private LinearLayout ingredientsTitleLayout;
    private LinearLayout stepsTitleLayout;
    private LinearLayout saveButtonLayout;
    private Recipe recipe;

    private HashMap<String, Integer> editTextIds = new HashMap<>();
    private HashMap<String, Integer> ingredientIds = new HashMap<>();

    private HashMap<String, Integer> stepsIds = new HashMap<>();

    private Button addIngredientsButton;
    private Button addStepsButton;

    private int numSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        nameAndDescriptionLayout = findViewById(R.id.name_desc_layout);
        ingredientsLayout = findViewById(R.id.ingredients_layout);
        stepsLayout = findViewById(R.id.steps_layout);
        ingredientsButtonLayout = findViewById(R.id.add_ingredient_button_layout);
        stepsButtonLayout = findViewById(R.id.add_steps_button_layout);
        ingredientsTitleLayout = findViewById(R.id.ingredients_title_layout);
        stepsTitleLayout = findViewById(R.id.steps_title_layout);
        saveButtonLayout = findViewById(R.id.save_button_layout);

        int recipeId = getIntent().getIntExtra("recipeId", -1);
        recipe = RecipeManager.getInstance().getRecipeById(recipeId);

        createEditField("name", recipe.getName());
        createEditField("description", recipe.getDescription());

        TextView ingredientsTitle = new TextView(this);
        ingredientsTitle.setText("Ingredients");
        ingredientsTitleLayout.addView(ingredientsTitle);

        initializeExistingIngredients(recipe, ingredientsLayout);


        Button addIngredientsButton = new Button(this);
        addIngredientsButton.setText("Add Ingredient");
        addIngredientsButton.setOnClickListener(v -> newIngredient());
        ingredientsButtonLayout.addView(addIngredientsButton);

        TextView stepsTitle = new TextView(this);
        stepsTitle.setText("Steps");
        stepsTitleLayout.addView(stepsTitle);

        initializeExistingSteps(recipe, stepsLayout);

        Button addStepsButton = new Button(this);
        addStepsButton.setText("Add Steps");
        addStepsButton.setOnClickListener(v -> newSteps());
        stepsButtonLayout.addView(addStepsButton);

        // Add a Save button
        Button saveButton = new Button(this);
        saveButton.setText("Save");
        saveButton.setOnClickListener(view -> save());
        saveButtonLayout.addView(saveButton);
    }

    private void createEditField(String label, String defaultValue) {
        int id = View.generateViewId();
        TextView textView = new TextView(this);
        textView.setText(label);
        EditText editText = new EditText(this);
        editText.setText(defaultValue);
        editText.setId(id);

        nameAndDescriptionLayout.addView(textView);
        nameAndDescriptionLayout.addView(editText);

        editTextIds.put(label, id);
    }

    private void createIngredientEditField(String label, String defaultValue) {
        int id = View.generateViewId();
        TextView textView = new TextView(this);
        textView.setText(label);
        EditText editText = new EditText(this);
        editText.setText(defaultValue);
        editText.setId(id);
        ingredientsLayout.addView(textView);
        ingredientsLayout.addView(editText);

        ingredientIds.put(label, id);

    }

    private void createStepsEditField(String label, String defaultValue) {
        int id = View.generateViewId();
        TextView textView = new TextView(this);
        textView.setText(label);
        EditText editText = new EditText(this);
        editText.setText(defaultValue);
        editText.setId(id);
        stepsLayout.addView(textView);
        stepsLayout.addView(editText);

        String stepsLabel = label.substring(5);
        stepsIds.put(stepsLabel, id);

    }

    private void save() {
        IngredientManager ingredientManager = IngredientManager.getInstance();

        String name = ((EditText) findViewById(editTextIds.get("name"))).getText().toString();
        recipe.setName(name);

        String description = ((EditText) findViewById(editTextIds.get("description"))).getText().toString();
        recipe.setDescription(description);

        HashMap<Ingredient, Integer> ingredients = new HashMap<>();
        for (Map.Entry<String, Integer> entry : ingredientIds.entrySet()) {
            String ingredientName = entry.getKey();
            int id = entry.getValue();
            int updatedValue = Integer.parseInt(((EditText) findViewById(id)).getText().toString());
            ingredients.put(ingredientManager.getIngredientByName(ingredientName), updatedValue);
        }

        recipe.setIngredients(ingredients);

        ArrayList<String> steps = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : stepsIds.entrySet()) {
            int stepIndex = Integer.parseInt(entry.getKey());
            int id = entry.getValue();
            String updatedValue = ((EditText) findViewById(id)).getText().toString();
            steps.add(updatedValue);
        }

        recipe.setSteps(steps);

        Toast.makeText(this, "Recipe updated successfully!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(EditRecipeActivity.this, RecipeDetailsActivity.class);
        intent.putExtra("recipeId", recipe.getId());
        startActivity(intent);
        finish();
    }

    private void initializeExistingIngredients(Recipe recipe, LinearLayout layout) {
        for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
            createIngredientEditField(entry.getKey().getName(), String.valueOf(entry.getValue()));
        }
    }

    private void initializeExistingSteps(Recipe recipe, LinearLayout layout) {
        for (int i = 0; i < recipe.getSteps().size(); i++) {
            createStepsEditField("Step " + (i + 1), recipe.getSteps().get(i));
            numSteps = i+1;
        }
    }

    private void newIngredient(){
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_ingredient, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Details")
                .setView(dialogView)
                .setPositiveButton("Add", (dialog, which) -> {
                    EditText ingredientName = dialogView.findViewById(R.id.name);
                    String ingredientNameText = ingredientName.getText().toString();
                    EditText quantity = dialogView.findViewById(R.id.quantity);
                    String quantityText = quantity.getText().toString();
                    createIngredientEditField(ingredientNameText, quantityText);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void newSteps(){
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_step, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Details")
                .setView(dialogView)
                .setPositiveButton("OK", (dialog, which) -> {
                    numSteps += 1;
                    EditText step = dialogView.findViewById(R.id.step);
                    String stepText = step.getText().toString();
                    createStepsEditField("Step " + numSteps, stepText);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}