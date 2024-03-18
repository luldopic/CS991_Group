package com.example.recipe;

import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;
import com.example.recipe.model.User;

import java.util.ArrayList;
import java.util.Map;

public class RecipeManager {
    private ArrayList<Recipe> recipes;
    private static RecipeManager instance;

    public RecipeManager() {
        this.recipes = new ArrayList<>();
    }

    public static RecipeManager getInstance() {
        if (instance == null) {
            instance = new RecipeManager();
        }
        return instance;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Recipe filterRecipeByName(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }
        return null;
    }

    // TODO: filterRecipeExcludesIngredient
    public ArrayList<Recipe> RecipeExcludesIngredient(Ingredient ingredient) {
        ArrayList<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (!recipe.getIngredients().containsKey(ingredient)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    public ArrayList<Recipe> RecipeContainsIngredient(Ingredient ingredient) {
        ArrayList<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().containsKey(ingredient)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    public ArrayList<Recipe> filterByAvailableIngredients(User user) {
        ArrayList<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            boolean hasAllIngredients = true;
            for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
                Ingredient ingredient = entry.getKey();
                int requiredQty = entry.getValue();
                if (!user.getPantry().containsKey(ingredient) || user.getPantry().get(ingredient) < requiredQty) {
                    hasAllIngredients = false;
                    break;
                }
            }
            if (hasAllIngredients) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    public void viewAllRecipes() {
        for (Recipe recipe : recipes) {
            recipe.printRecipe();
            System.out.println();
            System.out.println("------------------------------------------------");
            System.out.println();
        }
    }

    public void viewAllRecipesBrief() {
        for (Recipe recipe : recipes) {
            recipe.printRecipeBrief();
            System.out.println();
            System.out.println("------------------------------------------------");
            System.out.println();
        }
    }

    public void searchRecipeByNutrition(double minCalories,
                                        double maxCalories,
                                        double minProtein,
                                        double maxProtein,
                                        double minCarbs,
                                        double maxCarbs,
                                        double minFat,
                                        double maxFat) {

        ArrayList<Recipe> filteredRecipes = new ArrayList<>();
        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFat = 0;

        for (Recipe recipe : recipes) {

            totalCalories = recipe.getNutritionInfo().get("Calories");
            totalProtein = recipe.getNutritionInfo().get("Protein");
            totalCarbs = recipe.getNutritionInfo().get("Carbs");
            totalFat = recipe.getNutritionInfo().get("Fat");

            if (totalCalories >= minCalories &&
                    totalCalories <= maxCalories &&
                    totalProtein >= minProtein &&
                    totalProtein <= maxProtein &&
                    totalCarbs >= minCarbs &&
                    totalCarbs <= maxCarbs &&
                    totalFat >= minFat &&
                    totalFat <= maxFat) {

                filteredRecipes.add(recipe);
            }
        }
    }

    public void searchRecipeByName(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(name)) {
                recipe.printRecipeBrief();
            }
        }
    }

    public Recipe getRecipeById(int id) {
        for (Recipe recipe : recipes) {
            if (recipe.getId() == id) {
                return recipe;
            }
        }
        return null;
    }
}
