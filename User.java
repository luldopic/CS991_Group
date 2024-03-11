import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
public class User {
    private String username;
    private String password;
    private ArrayList<Cookbook> cookbookList;
    private String accountType;
    private ArrayList<Recipe> favourites;
    private boolean isLoggedIn;

    /*
    Constructor for a default user
     */
    public User() {
        this.username = "default";
        this.password = "default";
        this.accountType = "public";
        this.cookbookList = new ArrayList<>();
        this.cookbookList.add(getDefaultCookbook());
        this.favourites = new ArrayList<>();
        this.isLoggedIn = false;
    }

    /*
    Method to fetch the default cookbook from the app database to copy it to user cookbook
     */
    public static Cookbook getDefaultCookbook(){
        //Copy standard cookbook here (File type ? txt? SQL database?)
        return null;
    }

    /*
    Method to create an unique account
     */
    public void createAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountType = "personal";
        //Add details to SQL database
    }

    /*
    Method to check login details against saved database and login
     */
    public void login(String username, String password) {
        if (!username.equals(this.username)){
            throw new IllegalArgumentException("User not found");
        }
        if (!password.equals(this.password)) {
            //Return error wrong password
            return;
        }
        this.isLoggedIn = true;
    }

    /*
    Method to logout
     */
    public void logout() {
        this.isLoggedIn = false;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addRecipeToCookbook(Cookbook cookbook, Recipe recipe) {
        cookbook.addRecipe(recipe);
    }

    public void addRecipeToFavourite(Recipe recipe) {
        this.favourites.add(recipe);
    }

    public Cookbook getCookbook(String name) {
        for (Cookbook cookbook : cookbookList) {
            if (cookbook.getName().equals(name)){
                return cookbook;
            }
        }
        return null;
    }

    /*
    Returns all recipes that have a partial string match with search string.
    e.g. if the recipe name is "spinach lasagna", the method will return if the search
    string is "lasagna".
     */
    public ArrayList<Recipe> searchRecipeByName(String name) {
        ArrayList<Recipe> searchResult= new ArrayList<>();
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        for (Cookbook cookbook: cookbookList) {
            for (Recipe recipe : cookbook.getRecipeList()) {
                Matcher matcher = pattern.matcher(recipe.getName());
                boolean matchFound = matcher.find();
                if(matchFound){
                    searchResult.add(recipe);
                }
            }
        }
        return searchResult;
    }

    /*
    Return all recipe which have a specific ingredient (in object form)
     */
    public ArrayList<Recipe> filterRecipeByIngredient(Ingredient ingredient) {
        ArrayList<Recipe> searchResult= new ArrayList<>();
        for (Cookbook cookbook: cookbookList) {
            for (Recipe recipe : cookbook.getRecipeList()) {
                if(recipe.getIngredientList().containsKey(ingredient)){
                    searchResult.add(recipe);
                }
            }
        }
        return searchResult;
    }

    public ArrayList<Recipe> getFavourites() {
        return favourites;
    }



    //Methods
    /*
    Add Recipe
    Favourite Recipe
    Search Recipe
    View Recipe
    Filter Recipe
    Edit Recipe
     */
}
