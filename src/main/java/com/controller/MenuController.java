package com.controller;

import com.model.DietDTO;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public List<DietDTO> generateMenu(@RequestParam int n) throws Exception {
        List<DietDTO> diets = menuService.generate(n);
        return diets;
    }
}
