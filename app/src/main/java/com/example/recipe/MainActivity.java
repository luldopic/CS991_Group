package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeManager.getInstance();
        IngredientManager.getInstance();

        initializeIngredients();
        initializeRecipes();

        Button signInButton = findViewById(R.id.sign_in_button);
        Button registerButton = findViewById(R.id.register_button);
        Button viewRecipeButton = findViewById(R.id.view_recipe_button);

        viewRecipeButton.setOnClickListener(v -> viewRecipes());
        signInButton.setOnClickListener(v -> signIn());
        registerButton.setOnClickListener(v -> register());
    }

    public void viewRecipes() {
        Intent intent = new Intent(this, ViewRecipeActivity.class);
        startActivity(intent);
    }

    public void signIn() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void initializeIngredients(){
        IngredientManager ingredientManager = IngredientManager.getInstance();

        Ingredient breadSlices = new Ingredient("Bread Slices", 75, 3, 14, 1, "piece");
        Ingredient eggs = new Ingredient("Eggs", 155, 13, 1.1, 11, "piece");
        Ingredient avocado = new Ingredient("Avocado", 1.6, 0.02, 0.09, 0.15, "g");
        Ingredient garlic = new Ingredient("Garlic", 1.49, 0.064, 0.33, 0.005, "g");
        Ingredient oliveOil = new Ingredient("Olive Oil", 8.84, 0, 0, 1.00, "ml");
        Ingredient salt = new Ingredient("Salt", 0, 0, 0, 0, "tsp");
        Ingredient pepper = new Ingredient("Pepper", 5.7, 0.239, 1.47, 0.075, "tsp");
        Ingredient pasta = new Ingredient("Pasta", 1.31, 0.05, 0.25, 0.015, "g");
        Ingredient redPepperFlakes = new Ingredient("Red pepper flakes", 5.7, 0.239, 1.47, 0.075, "tsp");

        ingredientManager.addIngredient(breadSlices);
        ingredientManager.addIngredient(eggs);
        ingredientManager.addIngredient(avocado);
        ingredientManager.addIngredient(garlic);
        ingredientManager.addIngredient(oliveOil);
        ingredientManager.addIngredient(salt);
        ingredientManager.addIngredient(pepper);
        ingredientManager.addIngredient(pasta);
        ingredientManager.addIngredient(redPepperFlakes);
    }

    public void initializeRecipes() {

        RecipeManager recipeManager = RecipeManager.getInstance();
        IngredientManager ingredientManager = IngredientManager.getInstance();

        String nameAvocToast = "Avocado Toast";
        String descriptionAvocToast = "Toast topped with creamy mashed avocado and eggs";
        int servingSizeAvocToast = 1;
        String prepTimeAvocToast = "10 minutes";

        HashMap<Ingredient, Integer> ingredients = new HashMap<>();
        ingredients.put(ingredientManager.getIngredientByName("Bread Slices"), 2);
        ingredients.put(ingredientManager.getIngredientByName("Eggs"), 2);
        ingredients.put(ingredientManager.getIngredientByName("Avocado"), 100);
        ingredients.put(ingredientManager.getIngredientByName("Salt"), 1);
        ingredients.put(ingredientManager.getIngredientByName("Pepper"), 1);

        ArrayList<String> stepsAvocToast = new ArrayList<>();
        stepsAvocToast.add("Toast the bread slices.");
        stepsAvocToast.add("While the bread is toasting, fry or poach the eggs to your liking.");
        stepsAvocToast.add("Cut the avocado in half, remove the pit, and scoop out the flesh.");
        stepsAvocToast.add("Mash the avocado with a fork until it's creamy but still has some chunks.");
        stepsAvocToast.add("Spread the mashed avocado evenly over the toasted bread slices.");
        stepsAvocToast.add("Place the cooked egg on top of the mashed avocado.");
        stepsAvocToast.add("Season with salt, pepper, and any other desired toppings like chili flakes or fresh herbs.");

        Recipe sampleRecipeAvocToast = new Recipe(1, nameAvocToast, descriptionAvocToast, stepsAvocToast, servingSizeAvocToast, prepTimeAvocToast, ingredients);
        recipeManager.addRecipe(sampleRecipeAvocToast);


        // Sample recipe 2
        String namePastaAglio = "Pasta Aglio e Olio";
        String descriptionPastaAglio = "Simple pasta dish with garlic, olive oil, and red pepper flakes";
        int servingSizePastaAglio = 1;
        String prepTimePastaAglio = "15 minutes";

        HashMap<Ingredient, Integer> ingredientsPastaAO = new HashMap<>();
        ingredientsPastaAO.put(ingredientManager.getIngredientByName("Pasta"), 100);
        ingredientsPastaAO.put(ingredientManager.getIngredientByName("Olive Oil"), 15);
        ingredientsPastaAO.put(ingredientManager.getIngredientByName("Garlic"), 2);
        ingredientsPastaAO.put(ingredientManager.getIngredientByName("Red pepper flakes"), 1);

        ArrayList<String> stepsPastaAglio = new ArrayList<>();
        stepsPastaAglio.add("Cook spaghetti in salted boiling water until al dente. Reserve some pasta water and drain.");
        stepsPastaAglio.add("Heat olive oil in a pan over medium heat.");
        stepsPastaAglio.add("Add minced garlic and red pepper flakes, sauté until garlic is golden.");
        stepsPastaAglio.add("Add the cooked pasta to the pan. Toss with garlic, olive oil, and a bit of reserved pasta water if needed.");

        Recipe sampleRecipePasta = new Recipe(2, namePastaAglio, descriptionPastaAglio, stepsPastaAglio, servingSizePastaAglio, prepTimePastaAglio, ingredientsPastaAO);
        recipeManager.addRecipe(sampleRecipePasta);

    }
}