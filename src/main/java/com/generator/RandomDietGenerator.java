package com.generator;

import com.manager.MenuManager;
import com.model.DietDTO;
import com.model.DietTable;
import com.model.MenuDTO;
import com.utils.DietCalculator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDietGenerator implements DietGenerator {
    private MenuManager menuManager;
    DietCalculator dietCalculator = new DietCalculator();


    public RandomDietGenerator(MenuManager menuManager) {
        this.menuManager = menuManager;
    }

    @Override
    public DietTable generate(int n) {
        List<DietDTO> dietList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            DietDTO diet = generate();
            dietList.add(diet);
        }
        double score = dietCalculator.calculateScoreOfDietList(dietList);
        DietTable dietTable = new DietTable(dietList, score);
        System.out.println("=====주간 메뉴 점수 출력=====");
        System.out.println(String.format("%.2f", score));
        return dietTable;
    }

    private DietDTO generate() {
        List<MenuDTO> ricePool = menuManager.getRicePool();
        List<MenuDTO> soupPool = menuManager.getSoupPool();
        List<MenuDTO> kimchiPool = menuManager.getKimchiPool();
        List<MenuDTO> mainPool = menuManager.getMainPool();
        List<MenuDTO> sidePool = menuManager.getSidePool();

        MenuDTO rice = pickRandomly(ricePool);
        MenuDTO soup = pickRandomly(soupPool);
        MenuDTO kimchi = pickRandomly(kimchiPool);
        MenuDTO main = pickRandomly(mainPool);
        MenuDTO side1 = pickRandomly(sidePool);
        MenuDTO side2;
        do {
            side2 = pickRandomly(sidePool);
        } while (side2.equals(side1));
        List<MenuDTO> sideList = new ArrayList<>();
        sideList.add(side1);
        sideList.add(side2);

        DietDTO oneDayDiet = new DietDTO(rice, soup, kimchi, main, sideList);
        double score = dietCalculator.calculateScoreOfDiet(oneDayDiet);
        oneDayDiet.setScore(score);
        System.out.println("==식단 출력==");
        System.out.println(oneDayDiet);
        System.out.println("점수 출력");
        System.out.println(String.format("%.2f", score));
        return oneDayDiet;
    }

    private MenuDTO pickRandomly(List<MenuDTO> pool) {
        Random generator = new Random();
        int poolIndex = generator.nextInt(pool.size());
        return pool.get(poolIndex);
    }
}
