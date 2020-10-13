package com.model;

import java.util.List;

public class DietTable {
    private final List<DietDTO> dietList;
    private final double score;

    public DietTable(List<DietDTO> dietList, double score){
        this.dietList = dietList;
        this.score = score;
    }

    public List<DietDTO> getDietList() {
        return dietList;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "DietTable{" +
                "dietList=" + dietList +
                ", score=" + score +
                '}';
    }
}
