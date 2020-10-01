package com.model;


import java.util.List;

public class DietDTO {
    private final MenuDTO rice;
    private final MenuDTO soup;
    private final MenuDTO kimchi;
    private final MenuDTO main;
    private final List<MenuDTO> sideList;

    public DietDTO(MenuDTO rice, MenuDTO soup, MenuDTO kimchi, MenuDTO main, List<MenuDTO> sideList) {
        this.rice = rice;
        this.soup = soup;
        this.kimchi = kimchi;
        this.main = main;
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

    @Override
    public String toString() {
        return "DietDTO{" +
                "rice=" + rice +
                ", soup=" + soup +
                ", kimchi=" + kimchi +
                ", main=" + main +
                ", sideList=" + sideList +
                '}';
    }
}
