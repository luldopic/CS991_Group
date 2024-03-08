import java.lang.reflect.Array;
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

    public void addRecipeToCookbook(Cookbook cookbook, Recipe recipe) {
        return;
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
    }

    public ArrayList<Recipe> searchRecipeByName(String name) {
        ArrayList<Recipe> searchResult= new ArrayList<>();
        return null;
    }

    public ArrayList<Recipe> getFavourites() {
        return favourites;
    }

    public ArrayList<Recipe> filterRecipeBy(String something){
        return null;
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
