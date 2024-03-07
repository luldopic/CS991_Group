import java.util.HashMap;
public class Recipe {
    private String id;
    private String name;
    private HashMap<Ingredient, Integer> ingredientList;
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

    public void setIngredientList(HashMap<Ingredient, Integer> ingredientList) {
        this.ingredientList = ingredientList;
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
        this.cookTime = cookTime;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
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
