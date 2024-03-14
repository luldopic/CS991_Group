import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Recipe {
    private final String id;
    private String name;
    private final HashMap<Ingredient, Integer> ingredientList;  // ingredient with weight in grams
    private String instruction;
    private int prepTime;
    private int cookTime;
    private ArrayList<String> tags;

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
        tags = new ArrayList<>();
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
        this.tags = original.tags;
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

    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * Adds a tag to the recipe's tags list.
     * \n
     * Throws exception if null value given as param.
     * Removes commas if any from tag to add.
     * Does nothing if tag to add is empty or if it is already in the tags list.
     *
     * @param tag   tag to be added to tags list
     */
    public void addTag(String tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Tag must not be null");
        }
        if (tag.contains(",")) {
            tag = tag.replace(",", "");
        }
        if (tag.isEmpty()) {
            // do nothing
            return;
        }
        if (tags.contains(tag)) {
            // do nothing
            return;
        }
        tags.add(tag);
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
        for (Entry<Ingredient, Integer> entry : ingredientList.entrySet()) {
            Ingredient ingredient = entry.getKey();
            total += ingredientCalories(ingredient);
        }
        return total;
    }

    /**
     * Calculates the calorie value of a particular ingredient based on the weight included.
     *
     * @param ingredient    ingredient to calculate the calories for
     * @return  calories of ingredient according to its weight
     */
    public int ingredientCalories(Ingredient ingredient) {
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient was null");
        }
        if (!ingredientList.containsKey(ingredient)) {
            throw new IllegalArgumentException("Ingredient not included in recipe ingredient list");
        }

        if (ingredient.getCalories() == 0) {
            return 0;
        }
        double caloriesPerGram = ((double) ingredient.getCalories() / 100);
        double grams = (double) ingredientGrams(ingredient);

        return (int) (caloriesPerGram * grams);
    }

    public int ingredientGrams(Ingredient ingredient) {
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient was null");
        }
        if (!ingredientList.containsKey(ingredient)) {
            throw new IllegalArgumentException("Ingredient not included in recipe ingredient list");
        }

        // this gets value of key, which is the weight in grams
        return ingredientList.get(ingredient);
    }

    public int totalTime() {
        return prepTime + cookTime;
    }
}
