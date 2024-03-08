public class Ingredient {
    private String id;
    private String name;
    private String type;    // e.g. meat, fish, veg, fruit, grain, dairy?
    private int calories;   // calories per gram of ingredient
    // Could include other nutritional info:
    /*
    private int protein;
    private int fat;
    private int carbs;
    private int sugar;
    private int salt;
    */

    public Ingredient(String id, String name, String type, int calories) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.calories = calories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
