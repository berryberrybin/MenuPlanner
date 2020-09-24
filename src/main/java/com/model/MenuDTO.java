package com.model;

public class MenuDTO {
    private final int id;
    private final String name;
    private final String category;
    private final String country;
    private final String ingredient;
    private final String recipe;
    private final String color;

    public MenuDTO(int id, String name, String category, String country, String ingredient, String recipe, String color){
        this.id = id;
        this.name = name;
        this.category = category;
        this.country = country;
        this.ingredient = ingredient;
        this.recipe=recipe;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getCountry() {
        return country;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getRecipe() {
        return recipe;
    }

    public String getColor() {
        return color;
    }

}
