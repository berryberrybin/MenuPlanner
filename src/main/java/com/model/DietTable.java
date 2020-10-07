package com.model;

import java.util.List;

public class DietTable {
    private List<DietDTO> dietList;
    private double score;

    public DietTable(List<DietDTO> dietList, double score){
        this.dietList = dietList;
        this.score = score;
    }

    @Override
    public String toString() {
        return "DietTable{" +
                "dietList=" + dietList +
                ", score=" + score +
                '}';
    }
}
