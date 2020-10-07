package com.service;

import com.generator.DietGenerator;
import com.generator.RandomDietGenerator;
import com.manager.MenuManager;
import com.model.DietDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuManager menuManager;
    private DietGenerator dietGenerator;

    @PostConstruct
    private void initialize() {
        dietGenerator = new RandomDietGenerator(menuManager);
    }

    public List<DietDTO> generate(int n) {
        List<DietDTO> diets = dietGenerator.generate(n);
        return diets;
    }

}
