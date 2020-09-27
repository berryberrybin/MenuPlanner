package com.manager;

import com.model.MenuDTO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class MenuManager {
    private static String dburl = "jdbc:mysql://localhost:3306/menu_planner?serverTimezone=UTC";
    private static String dbuser = "root";
    private static String dbpasswd = "sb09130504@@";
    private List<MenuDTO> menuList;
    private List<MenuDTO> ricePool = new ArrayList<>();
    private List<MenuDTO> soupPool = new ArrayList<>();
    private List<MenuDTO> kimchiPool = new ArrayList<>();
    private List<MenuDTO> mainPool = new ArrayList<>();
    private List<MenuDTO> sidePool = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        menuList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("db read start");
        String sql = "SELECT * FROM menu";
        try (Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    int category = rs.getInt("category");
                    String country = rs.getString("country");
                    int ingredient = rs.getInt("ingredient");
                    String color = rs.getString("color");
                    int recipe = rs.getInt("recipe");
                    int id = rs.getInt("id");
                    MenuDTO menuDTO = new MenuDTO(id, name, category, country, ingredient, recipe, color);
                    menuList.add(menuDTO);
                    if (category == 1) {
                        mainPool.add(menuDTO);
                    } else if (category == 2) {
                        soupPool.add(menuDTO);
                    } else if (category == 3) {
                        sidePool.add(menuDTO);
                    } else if (category == 4) {
                        ricePool.add(menuDTO);
                    } else if (category == 5) {
                        kimchiPool.add(menuDTO);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //for (int i = 0; i < menuList.size(); i++) {
        //    MenuDTO menuDTO = menuList.get(i);
        //    System.out.println(menuDTO);
        //}
    }

    public List<MenuDTO> getMenuList() {
        return menuList;
    }

    public List<MenuDTO> getRicePool() {
        return ricePool;
    }

    public List<MenuDTO> getSoupPool() {
        return soupPool;
    }

    public List<MenuDTO> getKimchiPool() {
        return kimchiPool;
    }

    public List<MenuDTO> getMainPool() {
        return mainPool;
    }

    public List<MenuDTO> getSidePool() {
        return sidePool;
    }
}
