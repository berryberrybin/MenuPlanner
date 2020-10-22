package com.model;


import java.util.List;

public class DietDTO {
    private MenuDTO rice;
    private MenuDTO soup;
    private MenuDTO kimchi;
    private MenuDTO main;
    private List<MenuDTO> sideList;
    private double score;

    public DietDTO(MenuDTO rice, MenuDTO soup, MenuDTO kimchi, MenuDTO main, List<MenuDTO> sideList) {
        this.rice = rice;
        this.soup = soup;
        this.kimchi = kimchi;
        this.main = main;
        this.sideList = sideList;
    }

    public void setRice(MenuDTO rice) {
        this.rice = rice;
    }

    public void setSoup(MenuDTO soup) {
        this.soup = soup;
    }

    public void setKimchi(MenuDTO kimchi) {
        this.kimchi = kimchi;
    }

    public void setMain(MenuDTO main) {
        this.main = main;
    }

    public void setSideList(List<MenuDTO> sideList) {
        this.sideList = sideList;
    }

    public MenuDTO getRice() {
        return rice;
    }

    public MenuDTO getSoup() {
        return soup;
    }

    public MenuDTO getKimchi() {
        return kimchi;
    }

    public MenuDTO getMain() {
        return main;
    }

    public List<MenuDTO> getSideList() {
        return sideList;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "DietDTO{" +
                "rice=" + rice +
                ", soup=" + soup +
                ", kimchi=" + kimchi +
                ", main=" + main +
                ", sideList=" + sideList +
                ", score=" + score +
                '}';
    }
}
