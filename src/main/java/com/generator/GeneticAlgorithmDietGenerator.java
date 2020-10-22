package com.generator;


import com.manager.MenuManager;
import com.model.DietDTO;
import com.model.DietTable;
import com.model.MenuDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

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
            int num = (int) (Math.random() * 1) + 1;
            for (int j = 0; j < num; j++) {
                int randomGene = (int) (Math.random() * mutationGeneDiets.get(i).getDietList().size());
                DietDTO dietDTO = randomDietGenerator.generate(n).getDietList().get(randomGene);
                mutationGeneDiets.get(i).getDietList().set(randomGene, dietDTO);
            }
        }

        // TODO 돌연변이 유전자 생성
        List<DietTable> menuMutationGeneDiets = new ArrayList<>();

        for (int i = 0; i < eliteGeneDiets.size(); i++) {
            menuMutationGeneDiets.add(eliteGeneDiets.get(i));
            int num = (int) (Math.random()*10);
            for(int j=0;j<num;j++) {
                int randomGene = (int) (Math.random() * mutationGeneDiets.get(i).getDietList().size());
                int selectMenu = (int) (Math.random() * 5);
                switch (selectMenu) {
                    case 0:
                        MenuDTO newRice = pickRandomly(menuManager.getRicePool());
                        menuMutationGeneDiets.get(i).getDietList().get(randomGene).setRice(newRice);
                    case 1:
                        MenuDTO newSoup = pickRandomly(menuManager.getSoupPool());
                        menuMutationGeneDiets.get(i).getDietList().get(randomGene).setSoup(newSoup);

                    case 2:
                        MenuDTO newKimchi = pickRandomly(menuManager.getKimchiPool());
                        menuMutationGeneDiets.get(i).getDietList().get(randomGene).setKimchi(newKimchi);

                    case 3:
                        MenuDTO newMain = pickRandomly(menuManager.getMainPool());
                        menuMutationGeneDiets.get(i).getDietList().get(randomGene).setMain(newMain);

                    case 4:
                        MenuDTO newSide1;
                        MenuDTO side2 = menuMutationGeneDiets.get(i).getDietList().get(randomGene).getSideList().get(1);
                        do {
                            newSide1 = pickRandomly(menuManager.getSidePool());
                        } while (newSide1.equals(side2));
                        List<MenuDTO> newSides1 = new ArrayList<>();
                        newSides1.add(newSide1);
                        newSides1.add(side2);
                        menuMutationGeneDiets.get(i).getDietList().get(randomGene).setSideList(newSides1);

                    case 5:
                        MenuDTO newSide2;
                        MenuDTO side1 = menuMutationGeneDiets.get(i).getDietList().get(randomGene).getSideList().get(0);
                        do {
                            newSide2 = pickRandomly(menuManager.getSidePool());
                        } while (newSide2.equals(side1));
                        List<MenuDTO> newSides2 = new ArrayList<>();
                        newSides2.add(newSide2);
                        newSides2.add(side1);
                        menuMutationGeneDiets.get(i).getDietList().get(randomGene).setSideList(newSides2);
                }
            }

        }

        // TODO 새로운 유전자 채워넣기
        for (int i = 40; i < geneDiets.size(); i++) {
            geneDiets.set(i, randomDietGenerator.generate(n));
        }
        // TODO 가장 우월한 유전자 반환
        return geneDiets.get(0);
    }

    private MenuDTO pickRandomly(List<MenuDTO> pool) {
        Random generator = new Random();
        int poolIndex = generator.nextInt(pool.size());
        return pool.get(poolIndex);
    }
}
