import java.util.HashMap;
import java.util.Map.Entry;

public class Recipe {
    private final String id;
    private String name;
    private final HashMap<Ingredient, Integer> ingredientList;  // ingredient with weight in grams
    private String instruction;
    private int prepTime;
    private int cookTime;

    /**
     * Creates a blank recipe with a default name.
     * Used for creating new recipes.
     */
    public Recipe()
    {
        id = generateId();
        name = "New recipe";
        ingredientList = new HashMap<>();   // double check that this works
        instruction = "";
        prepTime = 0;
        cookTime = 0;
    }

    /**
     * Creates a copy of an existing recipe.
     * Generates a new unique ID for the copy recipe.
     *
     * @param original  original recipe to copy from
     */
    public Recipe(Recipe original) {
        id = generateId();
        this.name = original.name;
        this.ingredientList = original.ingredientList;
        this.instruction = original.instruction;
        this.prepTime = original.prepTime;
        this.cookTime = original.cookTime;
    }

    public String getId() {
        return id;
    }

    public String generateId() {
        return "0";
        // placeholder
        // TODO refactor to randomly generate ID instead
        // real id will be based on user id plus time so should be unique
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Ingredient, Integer> getIngredientList() {
        return ingredientList;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        if (cookTime < 0) {
            throw new IllegalArgumentException("Time to cook cannot be below 0");
        }
        this.cookTime = cookTime;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        if (prepTime < 0) {
            throw new IllegalArgumentException("Time to prep cannot be below 0");
        }
        this.prepTime = prepTime;
    }

    /**
     * Adds an ingredient to the recipe's ingredient list along with ingredient weight in grams.
     * If the ingredient already exists in the list, the value for weight will be overwritten.
     *
     * @param ingredient    ingredient to be added to recipe's ingredient list
     * @param grams         weight in grams for ingredient within this recipe
     */
    public void addIngredient(Ingredient ingredient, int grams) {
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient to add was null");
        }
        if (grams <= 0) {
            throw new IllegalArgumentException("Grams value must be number above 0");
        }
        ingredientList.put(ingredient, grams);
        // Note that hashmap does not allow duplicate keys
        // So if ingredient already exists in the list, the grams value will change
    }

    /**
     * Calculates the total calories for the recipe
     * by summing the calorie values for each ingredient by weight.
     *
     * @return  total calories values for all ingredients combined
     */
    public int totalCalories() {
        if (ingredientList.isEmpty()) {
            return 0;
        }
        // TODO test that this loop works as expected
        int total = 0;
        for (Entry<Ingredient, Integer> ingredient : ingredientList.entrySet()) {
            // Entry key = Ingredient object
            int baseCalories = ingredient.getKey().getCalories();

            // Entry value = weight in grams
            int grams = ingredient.getValue();

            int ingredientCalories = baseCalories * grams;
            total += ingredientCalories;
        }
        return total;
    }

    public int totalTime() {
        return prepTime + cookTime;
    }

    // Methods

    /*
    Add ingredients
    Edit ingredients
    Edit instructions
    Edit cooktime
    Edit preptime
    Edit Name
    Getter
        Name
        Cooktime
        Preptime
        Instructions
        Ingredients
     Get total nutrional value
        calories
        sugar
        carbs
        fat
        salts
     */
}
