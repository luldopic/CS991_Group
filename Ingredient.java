public class Ingredient {
    private String name;
    private double calories;
    private double protein;
    private double carbs;
    private double fat;
    private String unit;
    
    public Ingredient(String name, double calories, double protein, double carbs, double fat, String unit) {
        this.name = name;
        this.calories = calories;  // per 1 base unit
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFat() {
        return fat;
    }

    public String getUnit() {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}