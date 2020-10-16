package com.generator;


import com.manager.MenuManager;
import com.model.DietTable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GeneticAlgorithmDietGenerator implements DietGenerator {
    private final MenuManager menuManager;

    public GeneticAlgorithmDietGenerator(MenuManager menuManager) {
        this.menuManager = menuManager;
    }

    @Override
    public DietTable generate(int n) {
        List<DietTable> geneDiets = new ArrayList<>();
        // 초기 유전자 세트 생성
        RandomDietGenerator randomDietGenerator = new RandomDietGenerator(menuManager);
        while(geneDiets.size()<100) {
            geneDiets.add(randomDietGenerator.generate(n));
        }

        // score 기준 정렬
        geneDiets.sort(Comparator.comparing(DietTable::getScore).reversed());
        // TODO elite 유전자 추출

        // TODO 돌연변이 유전자 생성

        // TODO 새로운 유전자 채워넣기

        // TODO 가장 우월한 유전자 반환
        return geneDiets.get(0);
    }

}
