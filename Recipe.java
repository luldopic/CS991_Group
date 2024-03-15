import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private ArrayList<String> steps;
    private int servingSize;
    private String prepTime;
    private HashMap<Ingredient, Integer> ingredients;  // (Tomato, 500)
    private HashMap<String, Double> nutritionInfo;
    // TODO private ArrayList<String> tags;

    public Recipe(int id, String name, String description, ArrayList<String> steps, int servingSize, String prepTime, HashMap<Ingredient, Integer> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.steps = steps;
        this.servingSize = servingSize;
        this.prepTime = prepTime;
        this.ingredients = ingredients;
        this.nutritionInfo = nutritionInfo();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public int getServingSize() {
        return servingSize;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public HashMap<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public HashMap<String, Double> getNutritionInfo() {
        return nutritionInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }


    public void setIngredients(HashMap<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public void updateIngredient(Ingredient ingredient, int quantity) {
        if (ingredients.containsKey(ingredient)) {
            ingredients.put(ingredient, quantity);
        }
    }

    public HashMap<String, Double> nutritionInfo() {
        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFat = 0;
        HashMap<String, Double> nutrition = new HashMap<>();

        for (Map.Entry<Ingredient, Integer> entry : this.getIngredients().entrySet()) {
            Ingredient ingredient = entry.getKey();
            int quantity = entry.getValue();
            totalCalories += ingredient.getCalories() * quantity;
            totalProtein += ingredient.getProtein() * quantity;
            totalCarbs += ingredient.getCarbs() * quantity;
            totalFat += ingredient.getFat() * quantity;
        }

        nutrition.put("Calories", totalCalories);
        nutrition.put("Protein", totalProtein);
        nutrition.put("Carbs", totalCarbs);
        nutrition.put("Fat", totalFat);

        return nutrition;
    }

    public void printNutritionInfo() {
        System.out.println("Nutrition Information: ");
        for (Map.Entry<String, Double> entry : this.getNutritionInfo().entrySet()) {
            System.out.println("Total " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public void printRecipeBrief() {
        System.out.println("Recipe Name: " + this.getName());
        System.out.println("Description: " + this.getDescription());
        System.out.println("Calories: " + this.getNutritionInfo().get("Calories") + " kcal");
        System.out.println("Serving Size: " + this.getServingSize());
        System.out.println("Preparation Time: " + this.getPrepTime());
    }

    public void printRecipe() {
        System.out.println("Recipe Name: " + this.getName());
        System.out.println("Description: " + this.getDescription());
        System.out.println("Serving Size: " + this.getServingSize());
        System.out.println("Preparation Time: " + this.getPrepTime());
        System.out.println("Ingredients: ");
        for (Map.Entry<Ingredient, Integer> entry : this.getIngredients().entrySet()) {

            System.out.println("- " + entry.getKey().getName() + " (" + entry.getValue() + " " + entry.getKey().getUnit() + ")");
        }

        System.out.println("Steps: ");
        for (String step : this.getSteps()) {
            System.out.println("- " + step);
        }

        printNutritionInfo();
    }
}