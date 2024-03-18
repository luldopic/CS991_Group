package com.example.recipe;

import com.example.recipe.model.Ingredient;

import java.util.ArrayList;

public class IngredientManager {
    private ArrayList<Ingredient> ingredients;
    private static IngredientManager instance;

    private IngredientManager() {
        this.ingredients = new ArrayList<>();
    }

    public static IngredientManager getInstance() {
        if (instance == null) {
            instance = new IngredientManager();
        }
        return instance;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Ingredient getIngredientByName(String name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }
}
