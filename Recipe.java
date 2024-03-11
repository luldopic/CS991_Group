import java.util.HashMap;
public class Recipe {
    private String id;
    private String name;
    private HashMap<Ingredient, Integer> ingredientList;
    private String instruction;
    private int cookTime;
    private int prepTime;

    public Recipe(){

    }

    public String getName() {
        return name;
    }

    public HashMap<Ingredient, Integer> getIngredientList() {
        return ingredientList;
    }

    //Method
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
