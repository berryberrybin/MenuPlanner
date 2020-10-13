package com.controller;

import com.model.DietTable;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public DietTable generateMenu(@RequestParam int n) throws Exception {
        DietTable dietTable = menuService.generate(n);
        return dietTable;
    }
}
