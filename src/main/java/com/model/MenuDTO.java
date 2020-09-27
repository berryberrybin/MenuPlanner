package com.model;

public class MenuDTO {
    private final int id;
    private final String name;
    private final int category;
    private final String country;
    private final int ingredient;
    private final int recipe;
    private final String color;

    public MenuDTO(int id, String name, int category, String country, int ingredient, int recipe, String color){
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

    public int getCategory() {
        return category;
    }

    public String getCountry() {
        return country;
    }

    public int getIngredient() {
        return ingredient;
    }

    public int getRecipe() {
        return recipe;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", country='" + country + '\'' +
                ", ingredient=" + ingredient +
                ", recipe=" + recipe +
                ", color='" + color + '\'' +
                '}';
    }
}
