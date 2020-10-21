package com.generator;


import com.manager.MenuManager;
import com.model.DietDTO;
import com.model.DietTable;
import com.model.MenuDTO;

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
        while (geneDiets.size() < 100) {
            geneDiets.add(randomDietGenerator.generate(n));
        }

        // score 기준 정렬
        geneDiets.sort(Comparator.comparing(DietTable::getScore).reversed());
        // TODO elite 유전자 추출
        List<DietTable> eliteGeneDiets = new ArrayList<>();
        for (int i = 0; i < geneDiets.size() * 0.2; i++) {
            eliteGeneDiets.add(geneDiets.get(i));
        }

        // TODO 돌연변이 유전자 생성
        List<DietTable> mutationGeneDiets = new ArrayList<>();
        for (int i = 0; i < eliteGeneDiets.size(); i++) {
            mutationGeneDiets.add(eliteGeneDiets.get(i));
            int num = (int)(Math.random()*1)+1;
            for(int j=0;j<num;j++) {
                int randomGene = (int) (Math.random() * mutationGeneDiets.get(i).getDietList().size());
                DietDTO dietDTO = randomDietGenerator.generate(n).getDietList().get(randomGene);
                mutationGeneDiets.get(i).getDietList().set(randomGene,dietDTO);
            }
        }

        // TODO 돌연변이 유전자 생성

        // TODO 새로운 유전자 채워넣기
        for (int i = 40; i < geneDiets.size(); i++) {
            geneDiets.set(i,randomDietGenerator.generate(n));
        }
        // TODO 가장 우월한 유전자 반환
        return geneDiets.get(0);
    }

}
