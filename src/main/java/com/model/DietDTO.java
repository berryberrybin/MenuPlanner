package com.model;


import java.util.List;

public class DietDTO {
    private List<MenuDTO> menuList;

    public List<MenuDTO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuDTO> menuList) {
        this.menuList = menuList;
    }
}
