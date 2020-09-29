package com.generator;

import com.model.DietDTO;

import java.util.List;

public interface DietGenerator {
    List<DietDTO> generate(int n);
}
