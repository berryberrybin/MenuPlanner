package com.utils;

import com.model.DietDTO;

import java.util.*;

public class DietCalculator {
    public double calculateScoreOfDiet(DietDTO dietDTO){
        Set<String> colorScore = new HashSet<>();
        colorScore.add(dietDTO.getRice().getColor());
        colorScore.add(dietDTO.getSoup().getColor());
        colorScore.add(dietDTO.getKimchi().getColor());
        colorScore.add(dietDTO.getMain().getColor());
        for(int i=0;i<dietDTO.getSideList().size();i++){
            colorScore.add(dietDTO.getSideList().get(i).getColor());
        }

        Set<Integer> ingredientScore = new HashSet<>();
        ingredientScore.add(dietDTO.getRice().getIngredient());
        ingredientScore.add(dietDTO.getSoup().getIngredient());
        ingredientScore.add(dietDTO.getKimchi().getIngredient());
        ingredientScore.add(dietDTO.getMain().getIngredient());
        for(int i=0;i<dietDTO.getSideList().size();i++){
            ingredientScore.add(dietDTO.getSideList().get(i).getIngredient());
        }

        Set<Integer> recipeScore = new HashSet<>();
        recipeScore.add(dietDTO.getRice().getRecipe());
        recipeScore.add(dietDTO.getSoup().getRecipe());
        recipeScore.add(dietDTO.getKimchi().getRecipe());
        recipeScore.add(dietDTO.getMain().getRecipe());
        for(int i=0;i<dietDTO.getSideList().size();i++){
            recipeScore.add(dietDTO.getSideList().get(i).getRecipe());
        }

        String[] country = new String[6];
        country[0] = dietDTO.getRice().getCountry();
        country[1] = dietDTO.getSoup().getCountry();
        country[2] = dietDTO.getKimchi().getCountry();
        country[3]= dietDTO.getMain().getCountry();
        country[4] = dietDTO.getSideList().get(0).getCountry();
        country[5] = dietDTO.getSideList().get(1).getCountry();

        Map<String, Integer> countryOfMap = new HashMap<>();
        for(int i=0;i<country.length;i++) {
            if (countryOfMap.containsKey(country[i])){
                countryOfMap.put(country[i],countryOfMap.get(country[i])+1);
            } else{
                countryOfMap.put(country[i],1);
            }
        }
        double countryMax =0;
        double countryNum = countryOfMap.keySet().size();
        for(String key : countryOfMap.keySet()) {
            if (countryMax < countryOfMap.get(key)) {
                countryMax = countryOfMap.get(key);
            }
        }
        double countryScore = countryMax / countryNum;
        return colorScore.size()+ingredientScore.size()+recipeScore.size()+countryScore;
    }

    public double calculateScoreOfDietList(List<DietDTO> dietDTOList){

        int[] ingredient = new int[dietDTOList.size()];

        for(int i=0;i<dietDTOList.size();i++){
            ingredient[i]=dietDTOList.get(i).getMain().getIngredient();
        }
        Map<Integer,Integer> ingredientOfMap = new HashMap<>();
        for(int i=0;i<ingredient.length;i++){
            if(ingredientOfMap.containsKey(ingredient[i])){
                ingredientOfMap.put(ingredient[i],ingredientOfMap.get(ingredient[i])+1);
            }else{
                ingredientOfMap.put(ingredient[i],1);
            }
        }
        double ingredientMax =0;
        double ingredientNum = ingredientOfMap.keySet().size();
        for(int key : ingredientOfMap.keySet()) {
            if (ingredientMax < ingredientOfMap.get(key)) {
                ingredientMax = ingredientOfMap.get(key);
            }
        }
        double ingredientScore = ingredientMax / ingredientNum;



        String[] country = new String[dietDTOList.size()];
        for(int i=0;i<dietDTOList.size();i++){
            country[i]=dietDTOList.get(i).getMain().getCountry();
        }
        Map<String, Integer> countryOfMap = new HashMap<>();
        for(int i=0;i<country.length;i++) {
            if (countryOfMap.containsKey(country[i])){
                countryOfMap.put(country[i],countryOfMap.get(country[i])+1);
            } else{
                countryOfMap.put(country[i],1);
            }
        }
        double countryMax =0;
        double countryNum = countryOfMap.keySet().size();
        for(String key : countryOfMap.keySet()) {
            if (countryMax < countryOfMap.get(key)) {
                countryMax = countryOfMap.get(key);
            }
        }
        double countryScore = countryMax / countryNum;

        return ingredientScore+countryScore;
    }
}
