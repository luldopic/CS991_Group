import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cookbook {
    private ArrayList<Recipe> RecipeList;
    private String name;

    public Cookbook(String name) {
        this.name = name;
        this.RecipeList = new ArrayList<Recipe>();
    }
    //Methods
    /*
    Add Recipe
    Get name
    Change name
    Search by
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Recipe> getRecipeList() {
        return RecipeList;
    }

    public void addRecipe(Recipe recipe) {
        RecipeList.add(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        RecipeList.remove(recipe);
    }

    /*
    Returns all recipes that have a partial string match with search string.
    e.g. if the recipe name is "spinach lasagna", the method will return if the search
    string is "lasagna".
     */
    public ArrayList<Recipe> seachRecipeByName(String name) {
        ArrayList<Recipe> searchResult= new ArrayList<>();
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        for (Recipe recipe : RecipeList) {
            Matcher matcher = pattern.matcher(recipe.getName());
            boolean matchFound = matcher.find();
            if(matchFound){
                searchResult.add(recipe);
            }
        }
        return searchResult;
    }
}
