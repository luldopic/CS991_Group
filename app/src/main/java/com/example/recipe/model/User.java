package com.example.recipe.model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private int id;
    private String username;
    private String password;
    private ArrayList<Recipe> favouriteRecipes;
    private HashMap<Ingredient, Integer> pantry;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.favouriteRecipes = new ArrayList<>();
        this.pantry = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(ArrayList<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }

    public HashMap<Ingredient, Integer> getPantry() {
        return pantry;
    }

    public void setPantry(HashMap<Ingredient, Integer> pantry) {
        this.pantry = pantry;
    }

    public void markFavourite(Recipe recipe) {
        favouriteRecipes.add(recipe);
    }

    public void unmarkFavourite(Recipe recipe) {
        favouriteRecipes.remove(recipe);
    }

    public void addIngredientToPantry(Ingredient ingredient, int quantity) {
        pantry.put(ingredient, quantity);
    }

    public void removeIngredientFromPantry(Ingredient ingredient) {
        pantry.remove(ingredient);
    }

    public void printFavouriteRecipes() {
        for (Recipe recipe : favouriteRecipes) {
            recipe.printRecipeBrief();
        }
    }

    public void printPantry() {
        for (HashMap.Entry<Ingredient, Integer> entry : pantry.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue() + " " + entry.getKey().getUnit());
        }
    }
}