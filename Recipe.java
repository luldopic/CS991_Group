import java.util.HashMap;
public class Recipe {
    private String id;
    private String name;
    private HashMap<Ingredient, Integer> ingredientList;
    private String instruction;
    private int cookTime;
    private int prepTime;

    public Recipe(String name, HashMap<Ingredient, Integer> ingredientList,
                  String instruction, int cookTime, int prepTime)
    {
        id = generateId();
        this.name = name;
        this.ingredientList = ingredientList;
        this.instruction = instruction;
        this.cookTime = cookTime;
        this.prepTime = prepTime;
    }

    public Recipe (String name)
    {
        id = generateId();
        this.name = name;
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
