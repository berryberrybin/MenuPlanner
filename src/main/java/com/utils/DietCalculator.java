package com.utils;

import com.model.DietDTO;
import com.model.MenuDTO;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DietCalculator {
    public static double calculateScoreOfDiet(DietDTO dietDTO) {
        Set<String> colorScore = new HashSet<>();
        colorScore.add(dietDTO.getRice().getColor());
        colorScore.add(dietDTO.getSoup().getColor());
        colorScore.add(dietDTO.getKimchi().getColor());
        colorScore.add(dietDTO.getMain().getColor());
        for (MenuDTO side : dietDTO.getSideList()) {
            colorScore.add(side.getColor());
        }

        Set<Integer> ingredientScore = new HashSet<>();
        ingredientScore.add(dietDTO.getRice().getIngredient());
        ingredientScore.add(dietDTO.getSoup().getIngredient());
        ingredientScore.add(dietDTO.getKimchi().getIngredient());
        ingredientScore.add(dietDTO.getMain().getIngredient());
        for (MenuDTO side : dietDTO.getSideList()) {
            ingredientScore.add(side.getIngredient());
        }

        Set<Integer> recipeScore = new HashSet<>();
        recipeScore.add(dietDTO.getRice().getRecipe());
        recipeScore.add(dietDTO.getSoup().getRecipe());
        recipeScore.add(dietDTO.getKimchi().getRecipe());
        recipeScore.add(dietDTO.getMain().getRecipe());
        for (MenuDTO side : dietDTO.getSideList()) {
            recipeScore.add(side.getRecipe());
        }

        List<String> countryList = new ArrayList<>();
        countryList.add(dietDTO.getRice().getCountry());
        countryList.add(dietDTO.getSoup().getCountry());
        countryList.add(dietDTO.getKimchi().getCountry());
        countryList.add(dietDTO.getMain().getCountry());
        countryList.add(dietDTO.getSideList().get(0).getCountry());
        countryList.add(dietDTO.getSideList().get(1).getCountry());

        Map<String, Integer> countryOfMap = new HashMap<>();
        for (String country : countryList) {
            if (countryOfMap.containsKey(country)) {
                countryOfMap.put(country, countryOfMap.get(country) + 1);
            } else {
                countryOfMap.put(country, 1);
            }
        }
        double countryMax = 0;
        double countryNum = countryOfMap.keySet().size();
        for (String key : countryOfMap.keySet()) {
            if (countryMax < countryOfMap.get(key)) {
                countryMax = countryOfMap.get(key);
            }
        }
        double countryScore = countryMax / countryNum;
        return colorScore.size() + ingredientScore.size() + recipeScore.size() + countryScore;
    }

    public static double calculateScoreOfDietList(List<DietDTO> dietDTOList) {
        double avgScoreOfDiet = avgScoreOfDiet(dietDTOList);
        double ingredientScore = ingredientScore(dietDTOList);
        double countryScore = countryScore(dietDTOList);
        return ingredientScore + countryScore + avgScoreOfDiet;
    }

    private static double avgScoreOfDiet(List<DietDTO> dietDTOList) {
        return dietDTOList.stream().mapToDouble(DietCalculator::calculateScoreOfDiet).average().orElse(0);
    }

    private static double ingredientScore(List<DietDTO> dietDTOList) {
        List<Integer> ingredientList = new ArrayList<>();
        for (DietDTO dietDTO : dietDTOList) {
            ingredientList.add(dietDTO.getMain().getIngredient());
        }
        Map<Integer, Long> ingredientMap = ingredientList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        double ingredientMax = Collections.max(ingredientMap.values());
        double ingredientNum = ingredientMap.keySet().size();
        return ingredientMax / ingredientNum;

//        Map<Integer, Integer> ingredientMap = new HashMap<>();
//        for (int ingredient : ingredientList) {
//            ingredientMap.put(ingredient, ingredientMap.getOrDefault(ingredient, 0) + 1);
//        }
//        for (int key : ingredientMap.keySet()) {
//            if (ingredientMax < ingredientMap.get(key)) {
//                ingredientMax = ingredientMap.get(key);
//            }
//        }
    }

    private static double countryScore(List<DietDTO> dietDTOList) {
        List<String> countryList = new ArrayList<>();
        for (DietDTO dietDTO : dietDTOList) {
            countryList.add(dietDTO.getMain().getCountry());
        }
        Map<String, Long> countryMap = countryList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        double countryMax = Collections.max(countryMap.values());
        double countryNum = countryMap.keySet().size();
        return countryMax / countryNum;
    }
}
