import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// TODO error handling


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserManager userManager = new UserManager(); 
        RecipeManager recipeManager = new RecipeManager();


        // Ingredients
        Ingredient breadSlices = new Ingredient("Bread Slices", 75, 3, 14, 1, "piece"); 
        Ingredient eggs = new Ingredient("Eggs", 155, 13, 1.1, 11, "piece");
        Ingredient avocado = new Ingredient("Avocado", 1.6, 0.02, 0.09, 0.15, "g");
        Ingredient garlic = new Ingredient("Garlic", 1.49, 0.064, 0.33, 0.005, "g"); 
        Ingredient oliveOil = new Ingredient("Olive Oil", 8.84, 0, 0, 1.00, "ml"); 
        Ingredient salt = new Ingredient("Salt", 0, 0, 0, 0, "tsp");
        Ingredient pepper = new Ingredient("Pepper", 5.7, 0.239, 1.47, 0.075, "tsp");
        Ingredient pasta = new Ingredient("Pasta", 1.31, 0.05, 0.25, 0.015, "g"); 
        Ingredient redPepperFlakes = new Ingredient("Red pepper flakes", 5.7, 0.239, 1.47, 0.075, "tsp");


        // sample user
        User sampleUser = new User(1,"admin","admin");

        sampleUser.addIngredientToPantry(breadSlices, 10);
        sampleUser.addIngredientToPantry(eggs, 10);
        sampleUser.addIngredientToPantry(avocado, 500);
        sampleUser.addIngredientToPantry(salt, 500);
        sampleUser.addIngredientToPantry(pepper, 500);

        userManager.addUser(sampleUser);


        // Sample recipe 1
        String nameAvocToast = "Avocado Toast";
        String descriptionAvocToast = "Toast topped with creamy mashed avocado and eggs";
        int servingSizeAvocToast = 1;
        String prepTimeAvocToast = "10 minutes";

        HashMap<Ingredient, Integer> ingredients = new HashMap<>();
        ingredients.put(breadSlices, 2);
        ingredients.put(eggs, 2);
        ingredients.put(avocado, 100);
        ingredients.put(salt, 1);
        ingredients.put(pepper, 1);

        ArrayList<String> stepsAvocToast = new ArrayList<>();
        stepsAvocToast.add("Toast the bread slices.");
        stepsAvocToast.add("While the bread is toasting, fry or poach the eggs to your liking.");
        stepsAvocToast.add("Cut the avocado in half, remove the pit, and scoop out the flesh.");
        stepsAvocToast.add("Mash the avocado with a fork until it's creamy but still has some chunks.");
        stepsAvocToast.add("Spread the mashed avocado evenly over the toasted bread slices.");
        stepsAvocToast.add("Place the cooked egg on top of the mashed avocado.");
        stepsAvocToast.add("Season with salt, pepper, and any other desired toppings like chili flakes or fresh herbs.");

        Recipe sampleRecipeAvocToast = new Recipe(1, nameAvocToast, descriptionAvocToast, stepsAvocToast, servingSizeAvocToast, prepTimeAvocToast, ingredients);
        recipeManager.addRecipe(sampleRecipeAvocToast);


        // Sample recipe 2
        String namePastaAglio = "Pasta Aglio e Olio";
        String descriptionPastaAglio = "Simple pasta dish with garlic, olive oil, and red pepper flakes";
        int servingSizePastaAglio = 1;
        String prepTimePastaAglio = "15 minutes";

        HashMap<Ingredient, Integer> ingredientsPastaAglio = new HashMap<>();
        ingredientsPastaAglio.put(pasta, 100); 
        ingredientsPastaAglio.put(oliveOil, 30); 
        ingredientsPastaAglio.put(garlic, 10);
        ingredientsPastaAglio.put(redPepperFlakes, 1);

        ArrayList<String> stepsPastaAglio = new ArrayList<>();
        stepsPastaAglio.add("Cook spaghetti in salted boiling water until al dente. Reserve some pasta water and drain.");
        stepsPastaAglio.add("Heat olive oil in a pan over medium heat.");
        stepsPastaAglio.add("Add minced garlic and red pepper flakes, sauté until garlic is golden.");
        stepsPastaAglio.add("Add the cooked pasta to the pan. Toss with garlic, olive oil, and a bit of reserved pasta water if needed.");

        Recipe sampleRecipePasta = new Recipe(2, namePastaAglio, descriptionPastaAglio, stepsPastaAglio, servingSizePastaAglio, prepTimePastaAglio, ingredientsPastaAglio);
        recipeManager.addRecipe(sampleRecipePasta);

        //-----------------------------------------------------------------------------------
        // Simulate user interaction

        // login the user
        System.out.println("Sample account provided. username: admin, password: admin");

        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(3, username, password);  // only for showing the login functionality

        userManager.loginUser(user);

        System.out.println("-----------------------------");
        System.out.println();

        // view recipes
        System.out.println("Available recipes: ");
        System.out.println();
        recipeManager.viewAllRecipesBrief();

        System.out.println("Enter the name of the recipe you want to view: ");
        String recipeName = scanner.nextLine();

        Recipe selectedRecipe = recipeManager.filterRecipeByName(recipeName);

        selectedRecipe.printRecipe();

        System.out.println("-----------------------------");
        System.out.println();

        // Edit the recipe's description
        System.out.println("Edit the recipe's description");
        System.out.println();
        System.out.println("Enter the new description: ");
        String newDescription = scanner.nextLine();

        recipeManager.editRecipeDescription(selectedRecipe, newDescription);
        
        System.out.println();
        System.out.println("Updated recipe: ");
        System.out.println();
        selectedRecipe.printRecipeBrief();

        System.out.println("-----------------------------");
        System.out.println();

        // Add the recipe to the user's favourites
        System.out.println("Add the recipe to your favourites? (yes/no)");
        String answer = scanner.nextLine();

        if (answer.equals("yes")) {
            sampleUser.markFavourite(selectedRecipe);
            System.out.println();
            System.out.println("Recipe added to favourites!");
            System.out.println();

            System.out.println("Your saved recipes: ");
            for (Recipe recipe : sampleUser.getFavouriteRecipes()) {
                recipe.printRecipeBrief();
            }
        }

        System.out.println("-----------------------------");
        System.out.println();

        // Filter recipes by user's available ingredients
        System.out.println("Your pantry: ");
        System.out.println();
        sampleUser.printPantry();
        System.out.println();

        System.out.println("Filter recipes by available ingredients? (yes/no)");
        String filterAnswer = scanner.nextLine();

        if (filterAnswer.equals("yes")) {
            ArrayList<Recipe> filteredRecipes = recipeManager.filterByAvailableIngredients(sampleUser);
            System.out.println();
            System.out.println("Filtered recipes: ");
            System.out.println();
            for (Recipe recipe : filteredRecipes) {
                recipe.printRecipeBrief();
            }
        }

        System.out.println("-----------------------------");
        System.out.println();

        // TODO: Add a recipe

        // Remove a recipe
        System.out.println("Remove a recipe? (yes/no)");
        String removeAnswer = scanner.nextLine();

        if (removeAnswer.equals("yes")) {
            System.out.println("Enter the name of the recipe you want to remove: ");
            String removeRecipeName = scanner.nextLine();

            Recipe recipeToRemove = recipeManager.filterRecipeByName(removeRecipeName);
            recipeManager.removeRecipe(recipeToRemove);
            System.out.println();
            System.out.println("Recipe removed!");
            System.out.println();
            System.out.println("Available recipes: ");
            System.out.println();
            recipeManager.viewAllRecipesBrief();
        }

        scanner.close();

    }
}
